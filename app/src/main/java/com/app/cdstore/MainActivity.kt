package com.app.cdstore

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.cdstore.data.network.SupabaseClient.client
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.serialization.Serializable
import com.app.cdstore.data.model.Cd
import com.app.cdstore.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_store, R.id.navigation_profile))
        navView.setupWithNavController(navController)

      /*  setContent {
            MaterialTheme { // Usando MaterialTheme diretamente para evitar erro de tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CdsList()
                }
            }
        } */
    }

  /*  @Composable
    fun CdsList() {
        var cds by remember { mutableStateOf<List<Cd>>(listOf()) } // Correção do mutableStateOf
        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                cds = client.from("cd")
                    .select()
                    .decodeList<Cd>()
            }
        }

        LazyColumn {
            items(
                cds,
                key = { cd -> cd.id_cd }, // Usando id_cd como chave
            ) { cd ->
                Text(
                    text = cd.titulo, // Garantindo que título seja acessível e existe no Supabase
                    modifier = Modifier.padding(8.dp),
                )
            }
        }
    } */
}
