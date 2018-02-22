package id.co.imastudio.firstrxkotlin2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        //sample 1
//        Observable.just(1,3,4,45)
//                .map { t: Int ->  t.toString() }
////                .filter { t: String -> t.equals("3") }
//                .subscribe { t: String? ->  Log.d("data", t) }
//
//        //sample II
//    var disposable =  Flowable.just("2","4","5","4","1","56","6")
//                .map { t: String -> t.toDouble()  }
//                .filter { t: Double -> t > 5 }
//                .subscribe() { t: Double? ->  Log.d("data 2", t.toString()) }
//
//
//        disposable.dispose()


        //emitting
        Observable.create<String> { e ->

            edtInput.addTextChangedListener(object  : TextWatcher{
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    e.onNext(p0.toString())

                }

            })

        }
                //management thread
                .debounce(1000,TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                //ambil bola dari keranjang
                .subscribe { t: String? ->  texthasil.text = t }




    }
}
