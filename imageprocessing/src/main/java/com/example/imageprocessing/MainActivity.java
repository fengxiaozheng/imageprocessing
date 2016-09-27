package com.example.imageprocessing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.imageprocessing.cameo.CameoActivity;
import com.example.imageprocessing.chart.ChartActivity;
import com.example.imageprocessing.decoloration.DecolorationActivity;
import com.example.imageprocessing.gray.GrayActivity;
import com.example.imageprocessing.negative.NegativeActivity;
import com.example.imageprocessing.nostalgia.NostalgiaActivity;
import com.example.imageprocessing.old.OldActivity;
import com.example.imageprocessing.reversal.ReversalActivity;
import com.example.imageprocessing.saturation.SaturationActivity;
import com.example.imageprocessing.scratchcard.ScratchCardActivity;
import com.example.imageprocessing.gl.GlActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btn_scratchcard)
    Button btn_scratchcard;
    @BindView(R.id.btn_cameo)
    Button btn_cameo;
    @BindView(R.id.btn_decoloration)
    Button btn_decoloration;
    @BindView(R.id.btn_gray)
    Button btn_gray;
    @BindView(R.id.btn_negative)
    Button btn_negative;
    @BindView(R.id.btn_nostalgia)
    Button btn_nostalgia;
    @BindView(R.id.btn_old)
    Button btn_old;
    @BindView(R.id.btn_reversal)
    Button btn_reversal;
    @BindView(R.id.btn_saturation)
    Button btn_saturation;
    @BindView(R.id.btn_chart)
    Button btn_chart;
    @BindView(R.id.btn_gl)
    Button btn_gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_cameo:
                startactivity(CameoActivity.class);
                break;
            case R.id.btn_decoloration:
                startactivity(DecolorationActivity.class);
                break;
            case R.id.btn_gray:
                startactivity(GrayActivity.class);
                break;
            case R.id.btn_negative:
                startactivity(NegativeActivity.class);
                break;
            case R.id.btn_nostalgia:
                startactivity(NostalgiaActivity.class);
                break;
            case R.id.btn_old:
                startactivity(OldActivity.class);
                break;
            case R.id.btn_reversal:
                startactivity(ReversalActivity.class);
                break;
            case R.id.btn_saturation:
                startactivity(SaturationActivity.class);
                break;
            case R.id.btn_scratchcard:
                startactivity(ScratchCardActivity.class);
                break;
            case R.id.btn_chart:
                startactivity(ChartActivity.class);
                break;
            case R.id.btn_gl:
                startactivity(GlActivity.class);
                break;
            default:
                break;
        }
    }

    private void startactivity(Class clas) {
        startActivity(new Intent(this, clas));
    }
}
