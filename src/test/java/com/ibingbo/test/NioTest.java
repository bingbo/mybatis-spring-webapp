package com.ibingbo.test;

import org.junit.Test;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by bing on 2017/2/10.
 */
public class NioTest {

    private final String infile = "/Users/zhangbingbing/Work/github/mybatis-spring-webapp/README.md";
    private final String outfile = "/Users/zhangbingbing/Work/github/mybatis-spring-webapp/out.txt";

    @Test
    public void testBuffer() {
        //分配指定大小的缓冲区
        IntBuffer buffer = IntBuffer.allocate(10);
        //包装一个现有的数组
        int[] arr = new int[10];
        IntBuffer buffer1 = IntBuffer.wrap(arr);

        this.displayBuffer(buffer);
        for (int i = 0; i < 10; i++) {
            buffer.put(i);
        }
        this.displayBuffer(buffer);
        //把limit设为当前位置position，position设为0
        this.displayBuffer(buffer.flip());
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        this.displayBuffer(buffer);
        //limit不变，position设为0
        this.displayBuffer(buffer.rewind());
        this.displayBuffer(buffer.limit(5));
        //从头开始，把limit设为capacity,position设为0
        this.displayBuffer(buffer.clear());
        this.displayBufferData(buffer);
        this.displayBuffer(buffer);

        //创建子缓冲区，子缓冲区的数据与原缓冲区数据是共享的，修改子缓冲区数据，原缓冲区的部分数据也会发生变化
        buffer.position(3);
        buffer.limit(7);
        IntBuffer sliceBuffer = buffer.slice();
        displayBufferData(sliceBuffer);

        //创建只读缓冲区,与原缓冲区一样数据共享，但只能读
        IntBuffer readonlyBuffer = buffer.asReadOnlyBuffer();

    }


    @Test
    public void testDirectBuffer() throws IOException {
        FileInputStream fis = new FileInputStream(this.infile);
        FileChannel inChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream(this.outfile);
        FileChannel outChannel = fos.getChannel();


        /**
         * 分配一个直接缓冲区
         * 给定一个直接字节缓冲区，Java虚拟机将尽最大努 力直接对它执行本机I/O操作。
         * 也就是说，它会在每一次调用底层操作系统的本机I/O操作之前(或之后)，尝试避免将缓冲区的内容拷贝到一个中间缓冲区中 或者从一个中间缓冲区中拷贝数据
         */
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            //先清空缓冲区
            buffer.clear();
            int r = inChannel.read(buffer);
            if (r == -1) {
                break;
            }
            //从头开始
            buffer.flip();
            outChannel.write(buffer);
        }

    }

    @Test
    public void testMappedBuffer() throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile(this.infile,"rw");
        FileChannel fileChannel = accessFile.getChannel();

        /**
         * 可以锁定文件的一部分,
         * 要获取文件的一部分上的锁，您要调用一个打开的 FileChannel 上的 lock() 方法。
         * 注意，如果要获取一个排它锁，您必须以写方式打开文件。
         */
        FileLock lock=fileChannel.lock();
        lock.release();

        /**
         * 内存映射文件 I/O 是通过使文件中的数据神奇般地出现为内存数组的内容来完成的。
         * 这其初听起来似乎不过就是将整个文件读到内存中，但是事实上并不是这样。
         * 一般来说，只有文件中实际读取或者写入的部分才会送入（或者 映射 ）到内存中
         */
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        byte[] ss = new byte[1024];
        mappedByteBuffer.get(ss);
        System.out.println(new String(ss));
        fileChannel.close();

    }

    private void displayBuffer(Buffer buffer) {
        System.out.println("capacity: " + buffer.capacity());
        System.out.println("position: " + buffer.position());
        System.out.println("limit: " + buffer.limit());
        System.out.println("-------------------------");

    }

    private void displayBufferData(IntBuffer buffer) {
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println();
    }


}
