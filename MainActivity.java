package spqrlol.onoff;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "Made it to onCreate");

        // Toggle button instance, find by id given to togglebutton in activity_main.xml
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton1);

        // Listening for togglebutton toggle/click
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                        new AsyncTask<Integer, Void, Void>(){
                            @Override
                            protected Void doInBackground(Integer... params) {
                                try {
                                    Log.d("TAG", "Toggled on");
                                    executeSSHcommand("sudo python led.py");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }
                        }.execute(1);
                } else {
                    // The toggle is disabled
                    try {
                        new AsyncTask<Integer, Void, Void>(){
                            @Override
                            protected Void doInBackground(Integer... params) {
                                try {
                                    Log.d("TAG", "Toggled off");
                                    executeSSHcommand("sudo python led2.py");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }
                        }.execute(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    // Function for executing SSH connection and command is passed in
    public void executeSSHcommand(String command){
        Log.d("TAG", "Made it ot executeSSHcommand");

        // Set connection info here
        String user = "pi";
        String password = "Your password here";
        String host = "XXX.XXX.X.XX";
        int port=22;

        // Tries to connect and execute command here
        try{
            Log.d("TAG", "Trying to connect");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(10000);
            session.connect();
            ChannelExec channel = (ChannelExec)session.openChannel("exec");
            channel.setCommand(command);
            channel.connect();
            channel.disconnect();
            Log.d("TAG", "Success");
        }
        catch(Exception e){
            Log.d("TAG", "Error");
            Log.e("TAG", " Exception ", e);
        }
    }
}
