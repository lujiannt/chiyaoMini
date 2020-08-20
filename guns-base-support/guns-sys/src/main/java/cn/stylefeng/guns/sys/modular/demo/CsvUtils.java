package cn.stylefeng.guns.sys.modular.demo;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import lombok.Cleanup;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
    private static final Charset DEFAULT_CHARSET = Charset.forName("CP1252");

    /**
     * 读取Json String
     *
     * @param filePath
     * @param fields
     * @return
     * @throws IOException
     */
    public static String read(String filePath, String[] fields) throws IOException {
        return read(filePath, fields, DEFAULT_CHARSET);
    }

    /**
     * 读取Json String
     *
     * @param filePath
     * @param fields   Json key（按csv表头格式传入对应的列字段名）
     * @param charset
     * @return
     * @throws IOException
     */
    public static String read(String filePath, String[] fields, Charset charset) throws IOException {
        // 创建CSV读对象
        @Cleanup
        CsvReader csvReader = new CsvReader(filePath, ',', charset);
        // 读表头
        csvReader.readHeaders();
        // 获取表头
        String[] headers = csvReader.getHeaders();

        if (fields == null || fields.length <= 0) {
            fields = csvReader.getHeaders();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (csvReader.readRecord()) {
            sb.append("{");
            for (int i = 0; i < fields.length; i++) {
                sb.append("\"").append(fields[i]).append("\":\"").append(csvReader.get(headers[i])).append("\"");
                if (i < fields.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("},");
        }
        if (sb.lastIndexOf(",") > 0) {
            sb.deleteCharAt(sb.lastIndexOf(",")).append("]");
        }
        return sb.toString();
    }

    /**
     * 写入csv
     *
     * @param filePath
     * @param headers
     * @param content
     * @throws IOException
     */
    public static void write(String filePath, String[] headers, List<String[]> content) throws IOException {
        write(filePath, headers, content, DEFAULT_CHARSET);
    }

    /**
     * 写入csv文件
     *
     * @param filePath 写入路径
     * @param headers  表头
     * @param content  内容
     * @param charset  编码
     * @throws IOException
     */
    public static void write(String filePath, String[] headers, List<String[]> content, Charset charset) throws IOException {
        // 创建CSV写对象
        @Cleanup
        CsvWriter csvWriter = new CsvWriter(filePath, ',', charset);
        // 写表头
        csvWriter.writeRecord(headers);
        // 写行数据
        int i = 0;
        for (String[] rowTxt : content) {
            i++;
            csvWriter.writeRecord(rowTxt);
            if (i == 10000) {
                csvWriter.flush();
                i = 0;
            }
        }
        csvWriter.flush();
    }

    // 读取csv文件的内容
    public static ArrayList<String> readCsv(String filepath) throws Exception {
        File csv = new File(filepath); // CSV文件路径
        csv.setReadable(true);//设置可读
        csv.setWritable(true);//设置可写
        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(csv), "GBK"); //或GB2312,GB18030
            br = new BufferedReader(isr);

//            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        ArrayList<String> allString = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) // 读取到的内容给line变量
            {
                everyLine = line;
                System.out.println(everyLine);
                allString.add(everyLine);
            }
            System.out.println("csv表格中所有行数：" + allString.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allString;

    }


    public static List<List<Object>> getData(String fileName, String delimiter, boolean isAnsi){
        List<List<Object>> csvData = new ArrayList<>();
        try {
            Path filePath = Paths.get(fileName);
            Charset fileCharset;
            if (!isAnsi){
                fileCharset = StandardCharsets.UTF_8;
            }else{
                fileCharset = Charset.forName("CP1252");
            }
            Files.lines(filePath, fileCharset).forEach(s->{
                if(s == null || s.length() ==0){
                    csvData.add(null);
                    return;
                }
                List<Object> lineData = new ArrayList<>();
                csvData.add(lineData);
                if (isAnsi){
                    try {
                        s =new String(s.getBytes("CP1252"), "GBK");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                // 保留空
                for(String one:s.split(delimiter,-1)){
                    if (one == null || one.length() == 0){
                        lineData.add(null);
                    }else{
                        lineData.add(one);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }


    public static Object[][] readCSV(String csvFilePath) {
        //try{业务代码}catch(Exception e){如果做业务的过程中出了错，的异常处理逻辑}
        try {
            //容器：对象少的时候，直接把对象列出来；当对象很多的时候，要用一个容器装起来打包
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 这个不用背，只要看得懂会用就行。创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("CP1252"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            //boolean变量：真假true或者false
            while (reader.readRecord()) {
                System.out.println(reader.getRawRecord());
                //将一行的字符串按照“，”逗号分成多列，存放到String[]数组中
                //再将这个string[]放到list容器中存起来
                csvFileList.add(reader.getValues());
            }
            //数据取完了，关闭文件
            reader.close();



            // 遍历读取的CSV文件
            //for是一个整数次的循环，三个参数：最小值，最大值，增量，取个变量名存放每次循环的序列值

            Object[][] result=new Object[csvFileList.size()][csvFileList.get(0).length];
            for (int row = 0; row < csvFileList.size(); row++) {
                result[row]=csvFileList.get(row);
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        String csvFile = "D:\\tmp/test2222.csv";

        readCsv(csvFile);

//        readCSV(csvFile);

    }
}
