package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hw.Drivetrain;

// TODO:
// - Add methods for the hardware classes that send a list of telemetry strings
// - Change the constructor to allow the creator to list which devices telemetry should be
//   collected from

// Utility class for sending a standard set of telemetry for each hardware device.
public class StandardTelemetry {
    private final Telemetry telemetry;
    public StandardTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public void SendTelemetry() {

    }
}
