package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.Elevator;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

@Autonomous
public class auto extends LinearOpMode {

    private DcMotor frontLeft0, frontRight2, backLeft1, backRight3;
    Intake s_Intake;


    private ElapsedTime runtime = new ElapsedTime();


    public void drive (double rightFrontInches, double leftFrontInches,double backLeftInches, double backRightInches) {


        if (opModeIsActive()) {
            int rightFrontTarget = frontRight2.getCurrentPosition() + (int)( rightFrontInches* Constants.MecanumConstants.ticksToInch);
            int rightBackTarget = backRight3.getCurrentPosition() + (int)( backRightInches * Constants.MecanumConstants.ticksToInch);
            int leftFrontTarget = frontLeft0.getCurrentPosition() + (int)( leftFrontInches * Constants.MecanumConstants.ticksToInch);
            int leftBackTarget = backLeft1.getCurrentPosition() + (int)( backLeftInches* Constants.MecanumConstants.ticksToInch);


            frontLeft0.setTargetPosition(leftFrontTarget);
            backLeft1.setTargetPosition(leftBackTarget);
            frontRight2.setTargetPosition(rightFrontTarget);
            backRight3.setTargetPosition(rightBackTarget);

            frontRight2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontLeft0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight3.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontLeft0.setPower(-0.7);
            frontRight2.setPower(-0.7);
            backLeft1.setPower(0.7);
            backRight3.setPower(0.7);

            while (opModeIsActive() && (frontRight2.isBusy() || frontLeft0.isBusy() || backRight3.isBusy() || backLeft1.isBusy())){
            }

            frontLeft0.setPower(0);
            frontRight2.setPower(0);
            backLeft1.setPower(0);
            backRight3.setPower(0);
        }
    }

    @Override
    public void runOpMode() {
        frontLeft0 = hardwareMap.get(DcMotor.class, Constants.MecanumConstants.frontLeftMotor);
        frontRight2 = hardwareMap.get(DcMotor.class, Constants.MecanumConstants.frontRightMotor);
        backLeft1 = hardwareMap.get(DcMotor.class, Constants.MecanumConstants.backLeftMotor);
        backRight3 = hardwareMap.get(DcMotor.class, Constants.MecanumConstants.backRightMotor);


        frontLeft0.setDirection(Constants.MecanumConstants.invertLeft);
        frontRight2.setDirection(Constants.MecanumConstants.invertRight);
        backLeft1.setDirection(Constants.MecanumConstants.invertLeft);
        backRight3.setDirection(Constants.MecanumConstants.invertRight);

        try {
            s_Intake = new Intake(hardwareMap);

        } catch (Exception e) {

        }

        waitForStart();
        if (opModeIsActive()) {
            drive(-44,44, -44, 44);
        }
    }

}
