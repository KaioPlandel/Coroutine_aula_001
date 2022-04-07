package com.plandel.coroutineaula001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.plandel.coroutineaula001.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    val TAG = "Main"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//-------------------------------------------------------------------------------------------------
        //LIFECYCLESCOPE

//        binding.button.setOnClickListener {
//            lifecycleScope.launch {
//                while (true) {
//                    delay(1000L)
//                    Log.d(TAG, "Still running...")
//                }
//            }
//
//            GlobalScope.launch {
//                delay(5000L)
//                Log.d(TAG, "onCreate: ")
//                Intent(this@MainActivity,SecondActivity::class.java).also {
//                    startActivity(it)
//                    finish()
//                }
//            }
//        }



//-------------------------------------------------------------------------------------------------
        //ASYNC AND AWAIT

//        GlobalScope.launch(Dispatchers.IO) {
//            val time = measureTimeMillis {
//               val answer1 = async { doNetworkCall() }
//               val answer2 = async { doNetworkCall2() }
//
//                Log.d("TAG", "Answer1 is ${answer1.await()} ")
//                Log.d("TAG", "Answer2 is ${answer2.await()} ")
//            }
//            Log.d("TAG", "Request took $time ms.")
//        }


//-------------------------------------------------------------------------------------------------
        //JOBS, WAIT, CANCELATION

//        val job = GlobalScope.launch(Dispatchers.Default) {
//            Log.d("TAG", "Starting long running calculation...")
//            for (i in 30..40) {
//                if (isActive) {
//                    Log.d("TAG", "Result for 1 = $i: ${fib(i)}")
//                }
//            }
//            Log.d("TAG", "Ending long running calculation...")
//        }
//
//        runBlocking {
//            delay(2000L)
//            job.cancel()
//            Log.d("TAG", "Cancel job")
//
//
//            //job.join() join bloqueia a thread até que ela esteja finalizada
//        }

//-------------------------------------------------------------------------------------------------
        //RUN BLOCKING
        //ele afeta somente a mainThread outras threads não são bloqueadas
//        Log.d("TAG", "Before runBlocking")
//        runBlocking {
//            launch(Dispatchers.IO) {
//                delay(3000L)
//                Log.d("TAG", "Finish Io Coroutine 1")
//            }
//
//            launch(Dispatchers.IO) {
//                delay(3000L)
//                Log.d("TAG", "Finish Io Coroutine 2")
//            }
//            Log.d("TAG", "Before delay")
//            delay(5000L)
//            Log.d("TAG", "After delay")
//        }
//
//        Log.d("TAG", "Finish runBlocking")


        //A diferença do runBlocking do GlobalScope é que o run vai bloquear a thread

//        GlobalScope.launch(Dispatchers.Main) {
//            delay(1000L) // vai continuar podendo utlizar a ui
//        }
//
//        runBlocking {
//            delay(1000L) // ira bloquear a Ui
//        }


//-------------------------------------------------------------------------------------------------
        //COROUTINE CONTEXT

        //Dispatcher.Main -> Usado para alterações na Ui
        //Dispatcher.IO -> Fazer trabalhos como networking, escrever no banco de dados, ler e escrever arquivos
        //Dispatcher.Default -> para usar quando fazer calculos complexos e pesados

//        GlobalScope.launch(Dispatchers.IO) {
//
//            Log.d("TAG", "starting Coroutine ${Thread.currentThread().name}")
//            val answer = doNetworkCall() //aqui fazemos a chamada da requisição no IO
//            // em seguida para alterar a UI chamando o withContext e mudamos a Thread para fazer a mudança na ui
//
//            withContext(Dispatchers.Main){
//                Log.d("TAG", "starting Coroutine ${Thread.currentThread().name}")
//                binding.tvDummy.text = answer
//            }
//        }


//-------------------------------------------------------------------------------------------------
        //SUSPEND FUCTION
        //suspend fuction so pode ser chamado dentro de outra suspend ou dentro de um coroutineScope

//        GlobalScope.launch {
//            val networkCallAnswer = doNetworkCall()
//            val networkCallAnswer2 = doNetworkCall2()
//            Log.d("TAG", "onCreate: $networkCallAnswer")
//            Log.d("TAG", "onCreate: $networkCallAnswer2")
//        }

//--------------------------------------------------------------------------------------------------------
//         GLOBALSCOPE E DELAY
//
//        GlobalScope.launch {
//            delay(3000L) // apenas bloqueia a propria corrotina. não bloqueia toda a thread
//            Log.d("TAG", "onCreate: ${Thread.currentThread().name}")
//        }
//        Log.d("TAG", "onCreate: ${Thread.currentThread().name}")
    }

    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the answer"
    }

    suspend fun doNetworkCall2(): String {
        delay(3000L)
        return "This is the answer2"
    }

    fun fib(n: Int): Long {
        return if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }
}