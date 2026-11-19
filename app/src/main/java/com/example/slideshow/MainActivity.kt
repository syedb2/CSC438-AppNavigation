package com.example.appnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.example.appnavigation.data.Datasource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { NavigationApp() } }
    }
}

enum class AppScreen { Home, Contact }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val isContact = backStackEntry?.destination?.route == AppScreen.Contact.name
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(if (isContact) R.string.contact_title_bar else R.string.home_title)) },
                navigationIcon = {
                    if (isContact) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.back_home))
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = AppScreen.Home.name,
            modifier = Modifier.padding(innerPadding)) {
            composable(AppScreen.Home.name) {
                HomeScreen(onContactClicked = { navController.navigate(AppScreen.Contact.name) })
            }
            composable(AppScreen.Contact.name) { ContactScreen() }
        }
    }
}

@Composable
fun HomeScreen(onContactClicked: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Button(onClick = onContactClicked,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(stringResource(R.string.contact_title_bar))
        }
        LazyColumn(Modifier.weight(1f).fillMaxWidth().testTag("gallery"),
            contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            items(Datasource().loadItems()) { item ->
                Card {
                    Column(Modifier.padding(bottom = 12.dp)) {
                        Image(painterResource(item.image), contentDescription = stringResource(item.description),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxWidth().height(220.dp))
                        Text(stringResource(item.name), Modifier.padding(horizontal = 16.dp),
                            style = MaterialTheme.typography.titleLarge)
                        Text(stringResource(item.description), Modifier.padding(horizontal = 16.dp),
                            style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}

@Composable
fun ContactScreen() {
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painterResource(R.drawable.card_logo), stringResource(R.string.contact_picture),
            modifier = Modifier.size(128.dp))
        Spacer(Modifier.height(24.dp))
        Text(stringResource(R.string.contact_name), style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center)
        Text(stringResource(R.string.contact_title), style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center)
        Spacer(Modifier.height(24.dp))
        HorizontalDivider()
        Spacer(Modifier.height(24.dp))
        Text(stringResource(R.string.contact_school), textAlign = TextAlign.Center)
        Spacer(Modifier.height(12.dp))
        Text(stringResource(R.string.contact_github), textAlign = TextAlign.Center)
    }
}
