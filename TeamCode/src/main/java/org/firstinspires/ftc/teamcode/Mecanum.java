package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Mecanum {
    private DcMotor frontLeft0, frontRight2, backLeft1, backRight3;
    private IMU imu;
    public Mecanum(HardwareMap hardwareMap) {

        frontLeft0 = hardwareMap.get(DcMotor.class, Constants.MecanumConstants.frontLeftMotor);
        frontRight2 = hardwareMap.get(DcMotor.class, Constants.MecanumConstants.frontRightMotor);
        backLeft1 = hardwareMap.get(DcMotor.class, Constants.MecanumConstants.backLeftMotor);
        backRight3 = hardwareMap.get(DcMotor.class, Constants.MecanumConstants.backRightMotor);

        frontLeft0 = motorConfig(frontLeft0);
        frontRight2 = motorConfig(frontRight2);
        backLeft1 = motorConfig(backLeft1);
        backRight3 = motorConfig(backRight3);

        frontLeft0.setDirection(Constants.MecanumConstants.invertLeft);
        frontRight2.setDirection(Constants.MecanumConstants.invertRight);
        backLeft1.setDirection(Constants.MecanumConstants.invertLeft);
        backRight3.setDirection(Constants.MecanumConstants.invertRight);

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parmeters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        imu.initialize(parameters);
    }

    private void teleop(Gamepad gamepad1, boolean mode) {
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        if (gamepad1.options) {
            imu.resetYaw();
        }

        drive(y, x, rx, false);
    }

    public void resetGyro() {
        imu.resetYaw();
    }

    public void drive(double ySpeed, double xSpeed, double rot, boolean fieldOriented) {

        double rotY = ySpeed;
        double rotX = xSpeed;

        if(fieldOriented) {
            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            rotX = xSpeed * Math.cos(-botHeading) - ySpeed * Math.sin(-botHeading);
            rotY = xSpeed * Math.cos(-botHeading) + ySpeed * Math.sin(-botHeading);
        }

        double denominator = Math.max(Math.abs(ySpeed) + Math.abs(xSpeed) + Math.abs(rot), 1);
        double frontLeftPower = (rotY + rotX + rot) / denominator;
        double backLeftPower = (rotY - rotX + rot) / denominator;
        double frontRightPower = (rotY - rotX - rot) / denominator;
        double backRightPower = (rotY + rotX - rot) / denominator;

        setPower(frontLeftPower, backLeftPower, frontRightPower, backRightPower);
    }

    public void setPower(double frontLeft, double backLeft, double frontRight, double backRight) {
        frontLeft0.setPower(frontLeft);
        backLeft1.setPower(backLeft);
        frontRight2.setPower(frontRight);
        backRight3.setPower(backRight);
    }

    public void resetEncoders() {
        motorConfig(frontLeft0);
        motorConfig(backLeft1);
        motorConfig(frontRight2);
        motorConfig(backRight3);
    }

    private DcMotor motorConfig(DcMotor motor) {
        motor.setZeroPowerBehavior(Constants.MecanumConstants.neutralMode);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        return motor;
    }
}
