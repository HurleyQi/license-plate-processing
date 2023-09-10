package com.example.imageprocessing;

import javax.sound.midi.MidiChannel;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;

public class LicenseImageProcessor {
    private Mat plateImage;
    private Mat original;

    LicenseImageProcessor() {}

    LicenseImageProcessor(String fileName) {
        this.plateImage = Imgcodecs.imread(fileName);
        this.original = Imgcodecs.imread(fileName);
    }

    public Mat grayImage() {
        Mat result = new Mat();
        Imgproc.cvtColor(this.plateImage, result, Imgproc.COLOR_BGR2GRAY);
        this.plateImage = result;
        return result;
    }

    public Mat equalizeHist() {
        Mat result = new Mat();
        Imgproc.equalizeHist(this.plateImage, result);
        this.plateImage = result;
        return result;
    }

    public Mat binaryThreshold() {
        Mat result = new Mat();
        Imgproc.threshold(this.plateImage, result, 128, 255, Imgproc.THRESH_BINARY);
        this.plateImage = result;
        return result;
    }

    public Mat edgeDetection() {
        Mat result = new Mat();
        Imgproc.Canny(this.plateImage, result, 100, 200);
        this.plateImage = result;
        return result;
    }

    public Mat roiExtraction() {
        int x = 100, y = 150, width = 200, height = 100;
        Mat result = new Mat(this.plateImage, new Rect(x, y, width, height));
        this.plateImage = result;
        return result;
    }

    public Mat getPlateImage() {
        return this.plateImage;
    }

    public void setPlateImage(Mat plateImage) {
        this.plateImage = plateImage;
    }

    public Mat getOriginal() {
        return this.original;
    }

    public void setOriginla(Mat original) {
        this.original = original;
    }
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        nu.pattern.OpenCV.loadShared();
        LicenseImageProcessor t = new LicenseImageProcessor("license_test_1.jpg");
        HighGui.imshow("gray image", t.grayImage());
        HighGui.waitKey(0);
        HighGui.destroyAllWindows();

    }
}
