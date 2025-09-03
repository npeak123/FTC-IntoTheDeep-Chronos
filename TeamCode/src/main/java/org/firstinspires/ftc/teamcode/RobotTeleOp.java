package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.teamcode.Subsystems.Elevator;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.ArmConfig;

@TeleOp(name = Constants.OpModes.teleop, group = Constants.OpModes.linearOp)
public class RobotTeleOp extends OpMode {

    Mecanum s_Drivetrain;
    Intake s_Intake;
    Elevator s_Elevator;

    private ElapsedTime runtime = new ElapsedTime();

    boolean slomode = false;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        s_Drivetrain = new Mecanum(hardwareMap);
        try {
            s_Intake = new Intake(hardwareMap);
            s_Elevator = new Elevator(hardwareMap);
        } catch (Exception e) {

        }
        runtime.reset();

    }

    public void loop() {



        if (gamepad1.y) {
            if (slomode == true) {
                slomode = false;
            } else if (slomode == false) {
                slomode = true;
            }
        }
        s_Drivetrain.teleop(gamepad1, slomode);

        try {
            s_Intake.teleop(gamepad1, gamepad2);
            s_Elevator.teleop(gamepad1, gamepad2);
        } catch (Exception e) {

        }

        s_Drivetrain.periodic(telemetry);
        try {
            s_Intake.periodic(telemetry);
            s_Elevator.periodic(telemetry);
        } catch (Exception e) {

        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();

    }
}

