package org.firstinspires.ftc.teamcode.hw;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;


@TeleOp(name="Vision Test",group="TEST") public class Vision extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        AprilTagLibrary currentLibrary = AprilTagGameDatabase.getCurrentGameTagLibrary();

        AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder()
                .setTagLibrary(currentLibrary)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .build();

        VisionPortal visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .addProcessor(tagProcessor)
                .build();

        // visionPortal.start(); // Removed: VisionPortal does not have a start() method in the SDK

        waitForStart();
        while(opModeIsActive()) {
            if(tagProcessor.getDetections().size()>0){
                AprilTagDetection tag = tagProcessor.getDetections().get(0);
                telemetry.addData("Detected Tag ID", tag.id);
                telemetry.addData("Left/Right X (in)", tag.ftcPose.x);
                telemetry.addData("Up/Down Y (in)", tag.ftcPose.y);
                telemetry.addData("Forward Z (in)", tag.ftcPose.z);
                telemetry.addData("Yaw (deg)", tag.ftcPose.yaw);
                telemetry.addData("Pitch (deg)", tag.ftcPose.pitch);
                telemetry.addData("Roll (deg)", tag.ftcPose.roll);

                double distance = Math.sqrt(
                        tag.ftcPose.x * tag.ftcPose.x +
                        tag.ftcPose.y * tag.ftcPose.y +
                        tag.ftcPose.z * tag.ftcPose.z
                );
                double elevation = Math.toDegrees(Math.atan2(tag.ftcPose.y, tag.ftcPose.z));

                telemetry.addData("Distance (in)", distance);
                telemetry.addData("Elevation (deg)", elevation);
            } else {
                telemetry.addData("Status", "No tag detected");
            }
            telemetry.update();
        }



    }
}