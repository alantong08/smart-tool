package com.citi.alan.myproject.tess4j.util;

import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageWB {

    public static void main(String[] args) throws IOException {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String path = "C://Users//bt14118.NAM//git//smart-tool//src//main//resources//test-data//weixin2.png";
        String result = "C://Users//bt14118.NAM//git//smart-tool//src//main//resources//test-data//result2.png";
      //读取原图像
        Mat image  = Imgcodecs.imread(path,Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
//        Imgcodecs.imwrite(result, image);  
        
        Mat dst=new Mat();  
        Imgproc.adaptiveThreshold(image, dst, 200, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 25, 10); 
        Imgcodecs.imwrite(result, dst);  

    }

}
