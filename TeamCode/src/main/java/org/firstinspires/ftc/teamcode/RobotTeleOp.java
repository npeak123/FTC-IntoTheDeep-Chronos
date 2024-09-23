package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = Constants.OpModes.teleop, group = Constants.OpModes.linearOp)

public class RobotTeleOp extends LinearOpMode {

    Mecanum s_Drivetrain;

    private ElapsedTime runtime = new ElapsedTime();

    boolean slomo = false;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        s_Drivetrain = new Mecanum(hardwareMap);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            if (gamepad1.left_bumper) {
                slomo = true;
            } else {
                slomo = false;
            }
        }

        s_Drivetrain.teleop(gamepad1, slomo);

        s_Drivetrain.periodic(telemetry);

        telemetry.addData("Status:", "Run Time:" + runtime.toString());
        telemetry.update();
    }
}
