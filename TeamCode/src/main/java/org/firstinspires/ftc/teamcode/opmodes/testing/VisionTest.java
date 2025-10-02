package org.firstinspires.ftc.teamcode.opmodes.testing;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.hw.Vision;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name="Vision Test",group="TEST")
public class VisionTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Vision vision = new Vision(hardwareMap);
        waitForStart();
        while(opModeIsActive()) {
            vision.getTagTelemetry(telemetry, vision.getTags());
        }
    }
}
