package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hw.Drivetrain;

// Version of TeleopControl but without rules, time restrictions, a localization reference point,
// or anything else that isn't relevant for non-competition driving.
@TeleOp (name="FreeRoam", group="Test Opmodes")
public class FreeRoam extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Drivetrain drivetrain = new Drivetrain(hardwareMap);
        waitForStart();
        while(opModeIsActive()) {
            drivetrain.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x, false);
        }
    }
}
