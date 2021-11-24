package org.firstinspires.ftc.team6220_2021;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "BlueDuck", group = "Autonomous")
public class RealBlueDuck extends MasterOpMode{
    DcMotor motorBackLeft;
    DcMotor motorBackRight;
    DcMotor motorFrontLeft;
    DcMotor motorFrontRight;
    DcMotor motorDuck;
    DcMotor motorArm;
    Servo servoGrabber;
    Servo servoArm;
    double x = 0.7;
    @Override
    public void runOpMode() {
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorArm = hardwareMap.dcMotor.get("motorArm");
        motorDuck = hardwareMap.dcMotor.get("motorDuck");
        servoGrabber = hardwareMap.servo.get("servoGrabber");
        servoArm = hardwareMap.servo.get("servoArm");
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorDuck.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Set run mode of arm motor (encoders --> run to position)
        motorArm.setTargetPosition(0);
        motorArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        servoGrabber.setPosition(0.34);
        pauseMillis(500);
        servoArm.setPosition(0.01);
        waitForStart();
        motorBackLeft.setPower(0.6);
        motorBackRight.setPower(0.6);
        motorFrontLeft.setPower(0.6);
        motorFrontRight.setPower(0.6);
        pauseMillis(475);
        motorBackLeft.setPower(-0.1);
        motorBackRight.setPower(-0.1);
        motorFrontLeft.setPower(-0.1);
        motorFrontRight.setPower(-0.1);
        pauseMillis(100);
        motorBackLeft.setPower(0.1);
        motorBackRight.setPower(0.1);
        motorFrontLeft.setPower(0.1);
        motorFrontRight.setPower(0.1);
        pauseMillis(500);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorFrontRight.setPower(0);
        pauseMillis(100);
        x = 0.7;
        while (true) {
            motorDuck.setPower(x);
            pauseMillis(150);
            x += 0.05;
            telemetry.addData("duckPower", motorDuck.getPower());
            telemetry.update();
            if (x >= 0.85){
                pauseMillis(1500);
                motorDuck.setPower(-.1);
                pauseMillis(30);
                motorDuck.setPower(0);
                x=0.7;
                break;
            }
        }
        motorBackLeft.setPower(-0.6);
        motorBackRight.setPower(-0.6);
        motorFrontLeft.setPower(-0.6);
        motorFrontRight.setPower(-0.6);
        pauseMillis(300);
        motorBackLeft.setPower(-0.1);
        motorBackRight.setPower(-0.1);
        motorFrontLeft.setPower(-0.1);
        motorFrontRight.setPower(-0.1);
        pauseMillis(100);
        motorBackRight.setPower(0.6);
        motorFrontRight.setPower(0.6);
        pauseMillis(600);
        motorBackLeft.setPower(-0.1);
        motorBackRight.setPower(-0.1);
        motorFrontLeft.setPower(-0.1);
        motorFrontRight.setPower(-0.1);
        pauseMillis(100);
        motorBackLeft.setPower(0.6);
        motorBackRight.setPower(0.6);
        motorFrontLeft.setPower(0.6);
        motorFrontRight.setPower(0.6);
        pauseMillis(700);
        motorBackLeft.setPower(-0.1);
        motorBackRight.setPower(-0.1);
        motorFrontLeft.setPower(-0.1);
        motorFrontRight.setPower(-0.1);
        pauseMillis(100);
        motorArm.setTargetPosition(10);
        motorArm.setPower(0.9);
        pauseMillis(100);
    }
}