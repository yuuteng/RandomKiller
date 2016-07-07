package yuut.randomkiller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btnMin;
    private Button btnAdd;
    private EditText edtUserSum;
    private EditText edtWolfNum;
    private EditText edtPredictorNum;
    private TextView txtRecKill;
    private TextView txtRecCheck;
    private Button btnRandomKill;
    private Button btnRandomCheck;
    private int iUserSum;
    private int[] arrUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        btnMin = (Button) findViewById(R.id.btnMin);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        edtUserSum = (EditText) findViewById(R.id.edtUserSum);
        edtWolfNum = (EditText) findViewById(R.id.edtWolfNum);
        edtPredictorNum = (EditText) findViewById(R.id.edtPredictorNum);
        txtRecKill = (TextView) findViewById(R.id.txtRecKill);
        txtRecCheck = (TextView) findViewById(R.id.txtRecCheck);
        btnRandomKill = (Button) findViewById(R.id.btnRandomKill);
        btnRandomCheck = (Button) findViewById(R.id.btnRandomCheck);
        //加减玩家数量
        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int iUserSum = Integer.parseInt(edtUserSum.getText().toString().trim());
                    iUserSum = iUserSum-1;
                    edtUserSum.setText(Integer.toString(iUserSum));
                }catch (Exception e){
                     Toast.makeText(MainActivity.this,"输入格式有误请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int iUserSum = Integer.parseInt(edtUserSum.getText().toString().trim());
                    iUserSum = iUserSum + 1;
                    edtUserSum.setText(Integer.toString(iUserSum));
                }catch (Exception e){
                     Toast.makeText(MainActivity.this,"输入格式有误请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRandomCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iUserSum = Integer.parseInt(edtUserSum.getText().toString().trim());
                    int iPredictor =Integer.parseInt(edtPredictorNum.getText().toString().trim()) ;

                    arrUser = new int[iUserSum];
                    for (int i =0 ; i < iUserSum ; i++){
                        arrUser[i]= i + 1;
                    }
                    //生成首验
                    arrUser[iPredictor-1]=0;

                    Random random = new Random();
                    while (true){
                        int tmp = random.nextInt(iUserSum);
                        if (arrUser[tmp]!=0){
                            txtRecCheck.setText(Integer.toString(arrUser[tmp]));
                            break;
                        }
                    }
                    //恢复原数组
                    arrUser[iPredictor-1]=iPredictor;

                    Toast.makeText(MainActivity.this,"成功生成",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"输入格式有误请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRandomKill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iUserSum = Integer.parseInt(edtUserSum.getText().toString().trim());
                    arrUser = new int[iUserSum];
                    for (int i =0 ; i < iUserSum ; i++){
                        arrUser[i]= i + 1;
                    }

                    //获取狼人编号
                    String[] wolfNum = edtWolfNum.getText().toString().split(" ");
                    Random random = new Random();
                    //生成首刀
                    for (int i=0 ; i<wolfNum.length ; i++){
                        arrUser[Integer.parseInt(wolfNum[i])-1]=0;
                    }
//                    for (int i = 0 ;i<arrUser.length;i++){
//                        Log.i("info",Integer.toString(arrUser[i]));
//                    }
                    while (true){
                        int tmp = random.nextInt(iUserSum);
                        if (arrUser[tmp]!=0){
                            txtRecKill.setText(Integer.toString(arrUser[tmp]));
                            break;
                        }
                    }
                    Toast.makeText(MainActivity.this,"成功生成",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this,"输入格式有误请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
