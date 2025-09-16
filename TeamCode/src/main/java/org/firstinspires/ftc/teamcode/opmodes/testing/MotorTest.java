package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

// Auto class for testing individual motors and getting the active hardware mappings.
@TeleOp (name="FreeRoam", group="Test Opmodes")
public class MotorTest extends LinearOpMode {
    public void runOpMode() {
        DcMotor motor1 = hardwareMap.get(DcMotor.class, "motor1");
        DcMotor motor2 = hardwareMap.get(DcMotor.class, "motor2");
        DcMotor motor3 = hardwareMap.get(DcMotor.class, "motor3");
        DcMotor motor4 = hardwareMap.get(DcMotor.class, "motor4");
        while (opModeIsActive()) {
            if (gamepad1.a) {
                motor1.setPower(gamepad1.left_stick_y);
            } else if (gamepad1.b) {
                motor2.setPower(gamepad1.left_stick_y);
            } else if (gamepad1.x) {
                motor3.setPower(gamepad1.left_stick_y);
            } else if (gamepad1.y) {
                motor4.setPower(gamepad1.left_stick_y);
            }
        }
    }
}
