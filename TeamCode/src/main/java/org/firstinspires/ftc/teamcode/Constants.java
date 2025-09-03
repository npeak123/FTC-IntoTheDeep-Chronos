package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

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
        public static final double wheelD = 38; //38mm in inches
        public static final double gearRatio = 1;
        public static final double ticksToInch = (8192 / (wheelD * 3.14)) * 0.75; //(wheelD * PI) / ticksPerRev

    }

    public static final class ArmConstants {
        public static final String leftArm0 = "leftArm0";
        public static final String rightArm1 = "rightArm1";
    }

    public static final class ClawConstants {

        public static final String armExtender = "armExtender";
        public static final String intake = "intake";

        public static final Servo.Direction invertL = Servo.Direction.FORWARD;
        public static final Servo.Direction invertR = Servo.Direction.REVERSE;

        public static final double extendedAngle = .4;
        public static final double contractedAngle = .0;
    }

    public static final class ElevatorConstants {

        public static final String elevatorMotor = "elevatorMotor";
        public static final String moverServo = "moverServo";
        public static final String shooterServo = "shooterServo";
    }



}
