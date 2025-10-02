package org.firstinspires.ftc.teamcode.hw;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;

import java.util.List;
import java.util.Optional;

public class Vision {
    private AprilTagLibrary currentLibrary;
    private AprilTagProcessor tagProcessor;
    private VisionPortal visionPortal;

    public Vision(HardwareMap hardwareMap) {
        currentLibrary = AprilTagGameDatabase.getCurrentGameTagLibrary();
        tagProcessor = new AprilTagProcessor.Builder()
                .setTagLibrary(currentLibrary)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .build();
        // even though visionPortal isn't referenced here, it is still used by the AprilTagProcessor
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .addProcessor(tagProcessor)
                .build();
    }

    // note - list could be empty
    public List<AprilTagDetection> getTags() {
        return tagProcessor.getDetections();
    }

    // java doesn't have anonymous datastructures :(
    private static class tagPoseData {
        double distance;
        double elevation;
    }
    public tagPoseData getTagPoseData(AprilTagDetection tag) {
        tagPoseData data = new tagPoseData();
        data.distance = Math.sqrt(
                tag.ftcPose.x * tag.ftcPose.x +
                        tag.ftcPose.y * tag.ftcPose.y +
                        tag.ftcPose.z * tag.ftcPose.z
        );
        data.elevation = Math.toDegrees(Math.atan2(tag.ftcPose.y, tag.ftcPose.z));
        return data;
    }

    public void getTagTelemetry(Telemetry telemetry, List<AprilTagDetection> tags) {
        for (int i = 0; i < tags.size(); i++) {
            AprilTagDetection tag = tags.get(i);
            double distance = Math.sqrt(
                    tag.ftcPose.x * tag.ftcPose.x +
                            tag.ftcPose.y * tag.ftcPose.y +
                            tag.ftcPose.z * tag.ftcPose.z
            );
            double elevation = Math.toDegrees(Math.atan2(tag.ftcPose.y, tag.ftcPose.z));

            telemetry.addData("Detected Tag ID", tag.id);
            telemetry.addData("Left/Right X (in)", tag.ftcPose.x);
            telemetry.addData("Up/Down Y (in)", tag.ftcPose.y);
            telemetry.addData("Forward Z (in)", tag.ftcPose.z);
            telemetry.addData("Yaw (deg)", tag.ftcPose.yaw);
            telemetry.addData("Pitch (deg)", tag.ftcPose.pitch);
            telemetry.addData("Roll (deg)", tag.ftcPose.roll);

            telemetry.addData("Distance (in)", distance);
            telemetry.addData("Elevation (deg)", elevation);
        }
    }
}