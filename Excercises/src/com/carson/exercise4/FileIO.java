package com.carson.exercise4;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;

/**
 * Created by Carson on 17/02/2017.
 * CMPE 212 Exercise 4 - File I/O.
 */
public class FileIO {

    public static void main(String[] args) {
        String fileName = "test.txt"; //put one level above src automatically
        double[] data = generateDoubleArray(10000);
        long startTime = System.nanoTime();
        writeToFile(fileName, data);
        long endTime = System.nanoTime();
        System.out.println("Normal file:\n");
        displayResults(endTime - startTime, fileName);

        String fileNameBin = "testBin.dat";
        startTime = System.nanoTime();
        writeToBinaryFile(fileNameBin, data);
        endTime = System.nanoTime();
        System.out.println("Binary file:\n");
        displayResults(endTime - startTime, fileNameBin);

        String fileNameRABin = "testRABin.dat";
        startTime = System.nanoTime();
        writeToRABinFile(fileNameRABin, data);
        endTime = System.nanoTime();
        System.out.println("RA Bin file:\n");
        displayResults(endTime - startTime, fileNameRABin);
    }

    private static void displayResults(long runTime, String fileName) {
        System.out.println("Run time: " + runTime + "ns");
        System.out.println("File size: " + getFileSize(fileName) + "\n");
    }

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

    private static double[] generateDoubleArray(int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Math.random();
        }
        return arr;
    }

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
