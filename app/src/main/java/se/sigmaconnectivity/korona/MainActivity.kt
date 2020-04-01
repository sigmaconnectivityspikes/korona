package se.sigmaconnectivity.korona

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import se.sigmaconnectivity.korona.livedata.observe
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        infectedConfirmButton.setOnClickListener {
            mainViewModel.onHashIdSet(infectedInput.text.toString())
            infectedInput.setText("")
        }
        scanQrButton.setOnClickListener {
            mainViewModel.onStartScanQrCode()
        }
        mainViewModel.scanQrCode.observe(this, ::openScanQRCode)
        mainViewModel.scanQrCodeResult.observe(this, ::setInfectedInputText)
    }

    private fun openScanQRCode() {
        IntentIntegrator(this).apply {
            setOrientationLocked(false)
            initiateScan()
        }
    }

    private fun setInfectedInputText(text: String) {
        infectedInput.setText(text)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            val qrCode = result.contents
            if (qrCode == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                mainViewModel.onScanQrCodeResult(qrCode)
                Toast.makeText(this, "Scanned: $qrCode", Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
