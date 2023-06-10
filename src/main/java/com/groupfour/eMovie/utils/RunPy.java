package com.groupfour.eMovie.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RunPy {

    public static List<Integer> getRecommendIdByPy (String params) {

        List<Integer> movieIdList = new ArrayList<>();
        try {
            // 构建Python命令
            String pythonCommand = "python";
            String scriptPath = "src/main/java/com/groupfour/eMovie/utils/cF.py"; // 替换为实际的Python脚本文件路径和名称

            // 构建ProcessBuilder对象
            ProcessBuilder processBuilder = new ProcessBuilder(pythonCommand, scriptPath, params);

            // 执行Python脚本
            Process process = processBuilder.start();

            // 读取脚本输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            int i = 0;
            while ((line = reader.readLine()) != null) {
                i++;
                if (i < 2) {
                    continue;
                } else if (i > 17) {
                    break;
                }
                movieIdList.add(Integer.valueOf(line.split(" ")[0]));
            }

            // 等待脚本执行完成
            int exitCode = process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            return movieIdList;
        }
    }
}
