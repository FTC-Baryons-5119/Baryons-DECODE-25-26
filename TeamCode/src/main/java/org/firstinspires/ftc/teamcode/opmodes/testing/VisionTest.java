package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.hw.Vision;

@TeleOp(name="Vision Test",group="Test Opmodes")
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
