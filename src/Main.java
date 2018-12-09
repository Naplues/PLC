import nju.gzq.selector.FileHandle;
import test.PLCTest;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        // 测试单个特征
        //PLCTest.testSingleFeature(PLCTest.versions);
        // 测试两个特征
        //PLCTest.testGroupFeature(PLCTest.versions);
        // 测试Pid在候选较少的buckets上的结果
        //PLCTest.testLowDataSet(PLCTest.versions, 10, 4,8,9);
        //PLCTest.testLowDataSet(PLCTest.versions, 5, BaseFeature.POS, BaseFeature.DISTANCE, BaseFeature.ISCOMPONENT);
        //测试更多特征组合
        //PLCTest.testMoreFeature(PLCTest.versions);


        // 测试Pid方法
        PLCTest.testPid(PLCTest.versions, 9, 4,8);
        //测试特征选择器
        //testSelector();
        //for (String version : PLCTest.versions) combine(version);
    }


    public static void combine(String version) {
        String filePath = "C:\\Users\\gzq\\Desktop\\git\\Pid\\buckets_data\\form3\\" + version;
        File[] files = new File(filePath).listFiles();
        String text = "";
        String head = "";
        int count = 0;
        for (File file : files) {
            List<String> lines = FileHandle.readFileToLines(file.getPath());
            head = lines.get(0) + "\n";
            for (int i = 1; i < lines.size(); i++) {
                String[] a = lines.get(i).split(",");
                boolean allZero = true;
                for (int x = 1; x < a.length - 1; x++) {
                    if (!a[x].equals("0") && !a[x].equals("0.0")) {
                        allZero = false;
                        break;
                    }
                }
                if (!allZero) {
                    text += lines.get(i) + "\n";
                    count++;
                }
            }
        }
        text = head + text;
        //System.out.println(text);
        //FileHandle.writeStringToFile("C:\\Users\\gzq\\Desktop\\combine.csv", text);
        System.out.println(count);
    }

    /**
     * 测试特征选择器
     *
     * @throws Exception
     */
    public static void testSelector() throws Exception {
        //选择特征
        int featureNumber = 10;
        int neededFeatureNumber = 10;
        double threshold = 0.0;
        String outputPath = "C:\\Users\\gzq\\Desktop\\result";
        String fileType = "svg";
        int top = 10;
        new MySelector().start(featureNumber, outputPath, fileType, neededFeatureNumber, threshold, false, top, false);
    }
}
