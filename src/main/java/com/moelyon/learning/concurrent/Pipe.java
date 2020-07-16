package com.moelyon.learning.concurrent;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Pipe {
    static class ReaderThread implements Runnable {
        private PipedReader reader;

        public ReaderThread(PipedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            System.out.println("this is reader");
            int receive = 0;
            StringBuilder sb = new StringBuilder();
            try {
                while ((receive = reader.read()) != -1) {
                    sb.append((char)receive);
                }
                System.out.println("receive finish");
                System.out.println(sb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class WriterThread implements Runnable {

        private final PipedWriter writer;

        public WriterThread(PipedWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            System.out.println("this is writer");
            int receive = 0;
           
            try {
                Thread.sleep(2333L);
                writer.write("test");
            } catch (IOException | InterruptedException e) {

            }
            finally{
                if(writer!= null){
                    try {
                        writer.close();
                    } catch (IOException e) {
                    }
                }
            }
           
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        writer.connect(reader); // 这里注意一定要连接，才能通信

        new Thread(new ReaderThread(reader)).start();
        Thread.sleep(3500);
        new Thread(new WriterThread(writer)).start();
        Thread.sleep(3500);
        new Thread(new WriterThread(writer)).start();

        Thread.sleep(4000);
        // writer.close();
    }
}