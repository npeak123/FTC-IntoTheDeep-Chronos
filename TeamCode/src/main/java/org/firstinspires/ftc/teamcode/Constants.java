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

    }



}
