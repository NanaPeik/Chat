package ge.tsu.android.mziazezva;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private FragmentMzia fragmentMzia;
    private FragmentZezva fragmentZezva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        fragmentMzia= (FragmentMzia) getSupportFragmentManager().findFragmentById(R.id.fb);
//        fragmentZezva= (FragmentZezva) getSupportFragmentManager().findFragmentById(R.id.fa);

    }
}
