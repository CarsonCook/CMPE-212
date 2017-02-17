package com.carson.exercise4;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Created by Carson on 17/02/2017.
 * CMPE 212 Exercise 4 - File I/O.
 */
public class FileIO {

    public static void main(String[] args) {
        String fileName = "test.txt"; //put one level above src automatically
        double[] data = generateDoubleArray(10000);
        long startTime = System.currentTimeMillis();
        writeToFile(fileName, data);
        long endTime = System.currentTimeMillis();
        System.out.println("Normal file:\n");
        displayResults(endTime - startTime, fileName);

        String fileNameBin = "testBin.dat";
        startTime = System.currentTimeMillis();
        writeToBinaryFile(fileNameBin, data);
        endTime = System.currentTimeMillis();
        System.out.println("Binary file:\n");
        displayResults(endTime - startTime, fileNameBin);

        String fileNameRABin = "testRABin.dat";
        startTime = System.currentTimeMillis();
        writeToRABinFile(fileNameRABin, data);
        endTime = System.currentTimeMillis();
        System.out.println("RA Bin file:\n");
        displayResults(endTime - startTime, fileNameRABin);

        startTime = System.currentTimeMillis();
        ArrayList<Double> info = readFile(fileName);
        endTime = System.currentTimeMillis();
        System.out.println("Normal file in:\n");
        displayResults(endTime - startTime, fileName);
        System.out.println(info.toString());

        startTime = System.currentTimeMillis();
        info = readBinFile(fileNameBin);
        endTime = System.currentTimeMillis();
        System.out.println("Binary file in:\n");
        displayResults(endTime - startTime, fileNameBin);
        System.out.println(info.toString());

        startTime = System.currentTimeMillis();
        info = readRABinFile(fileNameRABin);
        endTime = System.currentTimeMillis();
        System.out.println("RA binary file in:\n");
        displayResults(endTime - startTime, fileNameRABin);
        System.out.println(info.toString());
    }

    /**
     * Shows the run time of reading/writing a file and its size/
     *
     * @param runTime  The run time.
     * @param fileName The file name/path.
     */
    private static void displayResults(long runTime, String fileName) {
        System.out.println("Run time: " + runTime + " ms");
        System.out.println("File size: " + getFileSize(fileName) + "\n");
    }

    /**
     * Writes a double array to a random access binary file.
     *
     * @param fileName The file to write to.
     * @param data     The data to write.
     */
    private static void writeToRABinFile(String fileName, double[] data) {
        ByteBuffer buffer = ByteBuffer.allocate(8 * data.length); //8 bits for one item in data
        DoubleBuffer dBuffer = buffer.asDoubleBuffer();
        for (double item : data) {
            dBuffer.put(item);
        }
        Path file = Paths.get(fileName);
        try (FileChannel channel = FileChannel.open(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            channel.position(0);
            channel.write(buffer);
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
    }

    /**
     * Reads a bunch of doubles on different lines from a random access binary file. Assumes there are 10000 doubles to read.
     *
     * @param fileName The file to read from.
     * @return ArrayList of type double holding all of the doubles in the file.
     */
    private static ArrayList<Double> readRABinFile(String fileName) {
        RandomAccessFile randFileIn = null;
        try {
            randFileIn = new RandomAccessFile(fileName, "r");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<Double> data = new ArrayList<>();
        try {
            for (int i = 0; i < 10000; i++) {
                data.add(randFileIn.readDouble());
            }
            randFileIn.close();
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
        return data;
    }

    /**
     * Writes a double array to a binary file.
     *
     * @param fileName The file to write to.
     * @param data     The data to write.
     */
    private static void writeToBinaryFile(String fileName, double[] data) {
        Path file = Paths.get(fileName);
        try (ObjectOutputStream writer = new ObjectOutputStream(new BufferedOutputStream(
                new FileOutputStream(file.toFile())))) {
            for (double item : data) {
                writer.writeDouble(item);
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
    }

    /**
     * Reads a bunch of doubles on different lines from a binary file. Assumes there are 10000 doubles to read.
     *
     * @param fileName The file to read from.
     * @return ArrayList of type double holding all of the doubles in the file.
     */
    private static ArrayList<Double> readBinFile(String fileName) {
        ArrayList<Double> data = new ArrayList<>();
        Path file = Paths.get(fileName);
        Double item = -1.1;
        try (ObjectInputStream reader = new ObjectInputStream(new BufferedInputStream(
                new FileInputStream(file.toFile())))) {
            int count = 0;
            while (count < 10000) { //know 10000 items in file
                data.add(reader.readDouble());
                count++;
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e + " " + item);
        }
        return data;
    }

    /**
     * Writes a double array to a file.
     *
     * @param fileName The file to write to.
     * @param data     The data to write.
     */
    private static void writeToFile(String fileName, double[] data) {
        Path file = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            for (double item : data) {
                writer.write(String.valueOf(item));
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
    }

    /**
     * Reads a bunch of doubles on different lines from a file.
     *
     * @param fileName The file to read from.
     * @return ArrayList of type double holding all of the doubles in the file.
     */
    private static ArrayList<Double> readFile(String fileName) {
        ArrayList<Double> data = new ArrayList<>();
        Path file = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = reader.readLine();
            while (line != null) {
                data.add(Double.parseDouble(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
        return data;
    }

    /**
     * Generates a double array of size "size" containing random values from 0 to 1
     *
     * @param size The size of the array.
     * @return A double array with random values.
     */
    private static double[] generateDoubleArray(int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Math.random();
        }
        return arr;
    }

    /**
     * Finds the size of a file in bytes.
     *
     * @param fileName The path/name of the file.
     * @return long that is the size of the file in bytes.
     */
    private static long getFileSize(String fileName) {
        long fileSize = 0;
        try {
            fileSize = Files.size(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
        return fileSize;
    }
}
