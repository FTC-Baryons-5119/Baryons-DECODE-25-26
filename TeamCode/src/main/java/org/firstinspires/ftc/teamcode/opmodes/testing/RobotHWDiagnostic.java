package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Collections;
import java.util.List;

// Auto class for testing individual motors and getting the active hardware mappings.
@TeleOp
public class RobotHWDiagnostic extends LinearOpMode {
    public void runOpMode() {
        // Detect and log all connected motors
        List<DcMotor> motors = hardwareMap.getAll(DcMotor.class);
        List<DcMotor> sortedMotors = Collections.emptyList();
        telemetry.addLine("CONNECTED MOTORS:");
        // this code will probably break
        for (int i = 0; i < motors.size(); i++) {
            DcMotor x = motors.get(i);
            telemetry.addLine("\t" + x.getPortNumber() + ": " + x.getDeviceName());
            sortedMotors.add(x.getPortNumber(), x);
        }
        while (true) {

        }
    }
}
