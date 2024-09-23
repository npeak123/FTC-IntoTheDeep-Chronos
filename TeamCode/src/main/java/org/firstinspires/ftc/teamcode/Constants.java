package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Constants {

    public static final class OpModes {
        public static final String linearOp = "Linear Opmode";
        public static final String teleop = "Teleop";
    }

    public static final class MecanumConstants {

        public static final String frontLeftMotor = "frontLeft";
        public static final String frontRightMotor = "frontRight";
        public static final String backLeftMotor = "backLeft";
        public static final String backRightMotor = "backRight";

        public static final DcMotor.Direction invertRight = DcMotor.Direction.FORWARD;
        public static final DcMotor.Direction invertLeft = DcMotor.Direction.REVERSE;
        public static final DcMotor.ZeroPowerBehavior neutralMode = DcMotor.ZeroPowerBehavior.BRAKE;

        public static final double ticksPerRev = 8192;
        public static final double wheelD = 38 / 25.4; //38mm in inches
        public static final double gearRatio = 1;
        public static final double ticksToInch = 1; //(wheelD * PI) / ticksPerRev

    }



}
