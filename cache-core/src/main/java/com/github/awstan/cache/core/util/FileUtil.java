package com.github.awstan.cache.core.util;

/**
 * @Author pw7563
 * @Date 2024/7/16 10:43
 * usage
 */
import java.io.*;
import java.nio.file.*;
import java.util.List;

public class FileUtil {

    /**
     * 检查文件是否存在
     * @param path 文件路径
     * @return 如果文件存在，返回 true；否则返回 false
     */
    public static boolean exists(String path) {
        return Files.exists(Paths.get(path));
    }

    /**
     * 创建文件
     * @param path 文件路径
     * @throws IOException 如果创建文件失败，抛出异常
     */
    public static void createFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    /**
     * 向文件追加内容
     * @param path 文件路径
     * @param lines 需要追加的内容
     * @throws IOException 如果文件写入失败，抛出异常
     */
    public static void append(String path, List<String> lines) throws IOException {
        Path filePath = Paths.get(path);
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND)) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    /**
     * 从文件读取内容
     * @param path 文件路径
     * @return 文件内容
     * @throws IOException 如果文件读取失败，抛出异常
     */
    public static List<String> readAllLines(String path) throws IOException {
        Path filePath = Paths.get(path);
        return Files.readAllLines(filePath);
    }

    public static void main(String[] args) {
        String dbPath = "1.txt";
        List<String> bufferList = List.of("Hello, World!", "This is a test.");

        try {
            // 1. 创建文件
            if (!FileUtil.exists(dbPath)) {
                FileUtil.createFile(dbPath);
            }

            // 2. 持久化追加到文件中
            FileUtil.append(dbPath, bufferList);

            // 3. 清空 buffer 列表
            bufferList.clear();

            // 4. 读取并打印文件内容
            List<String> content = FileUtil.readAllLines(dbPath);
            content.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
