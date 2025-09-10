package org.firstinspires.ftc.teamcode.hw;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

// Mecanum drivetrain only.
public class Drivetrain {
    private final DcMotor backLeft;
    private final DcMotor backRight;
    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final IMU imu;

    // Creates an uncorrected (manual) Drivetrain that uses an IMU
    public Drivetrain(HardwareMap hardwareMap) {
        backLeft = hardwareMap.get(DcMotor.class,"backLeft");
        backRight = hardwareMap.get(DcMotor.class,"backRight");
        frontLeft = hardwareMap.get(DcMotor.class,"frontLeft");
        frontRight = hardwareMap.get(DcMotor.class,"frontRight");
        imu = hardwareMap.get(IMU.class, "imu");
    }

    // Creates a Drivetrain with PedroPathing's DVA (Drive Vector Algorithm).
    // public Drivetrain(HardwareMap hardwareMap, Constants constants) {
    //
    // }

    // All of these inputs should be within (-1,1) or you will get weird behavior
    // if fieldCentric is true, inputRot should be an angle in radians
    public void drive(double inputX, double inputY, double inputRot, boolean fieldCentric) {
        if (fieldCentric) {
            double rotX = inputX*Math.cos(-inputRot) - inputY*Math.sin(-inputRot);
            double rotY = inputX*Math.sin(-inputRot) + inputY*Math.cos(-inputRot);

            inputX = rotX;
            inputY = rotY;
        }
        double divisor = Math.max(Math.abs(inputY) + Math.abs(inputX) + Math.abs(inputRot), 1);
        backLeft.setPower((inputY - inputX + inputRot)/divisor);
        backRight.setPower((inputY + inputX - inputRot)/divisor);
        frontLeft.setPower((inputY + inputX + inputRot)/divisor);
        frontRight.setPower((inputY - inputX - inputRot)/divisor);
    }
}
