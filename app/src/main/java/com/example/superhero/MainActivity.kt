@file:Suppress("FunctionName")

package com.example.superhero

import android.os.Bundle
import android.text.Layout.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superhero.ui.theme.SuperHeroTheme
import com.example.superheroapp.data.Hero
import com.example.superheroapp.data.HeroesRepository.heroes

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperHeroApp()
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun SuperHeroApp(
    modifier:Modifier = Modifier
    ){
        Scaffold(
            topBar = {
                SuperHeroTopBar()
            }
        ) {
            it->LazyColumn(
            contentPadding = it,
            modifier = modifier
                .padding(bottom = dimensionResource(R.dimen.padding_small))
        ){
            items(heroes){
                SuperHeroItem(
                    hero = it,
                )
            }
        }
        }
}
@ExperimentalMaterial3Api
@Composable
fun SuperHeroTopBar(){
    CenterAlignedTopAppBar(
        title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
    )
}
@Composable
fun SuperHeroItem(
    hero: Hero,
    modifier:Modifier = Modifier
){
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
            .padding(start = dimensionResource(R.dimen.padding_small),
                end = dimensionResource(R.dimen.padding_small)
            )
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    dimensionResource(R.dimen.padding_medium)
                )
                .size(dimensionResource(R.dimen.height))
        ){
            Column(
                modifier = Modifier
                    .weight(1f)
            ){
                HeroDescription(hero.nameRes,hero.descriptionRes,modifier)
            }

            Spacer(Modifier.width(16.dp))

            HeroIcon(hero.imageRes)
        }
    }
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
}

@Composable
private fun HeroDescription(
    nameRes:Int,
    descriptionRes:Int,
    modifier: Modifier = Modifier
){
    Text(
        text = stringResource(nameRes),
        style = MaterialTheme.typography.displaySmall
    )
    Text(
        text = stringResource(descriptionRes),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun HeroIcon(
    imageRes:Int,
    modifier: Modifier = Modifier
){
    Box (
        modifier = modifier
            .size(dimensionResource(R.dimen.height))
            .clip(RoundedCornerShape(8.dp))
    ){
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
                .size(dimensionResource(R.dimen.image_size))
        )
    }
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun SuperHeroPreview(){
    SuperHeroTheme(darkTheme = false) {
        SuperHeroApp()
    }
}


@ExperimentalMaterial3Api
@Preview
@Composable
fun SuperHeroPreviewDark(){
    SuperHeroTheme(darkTheme = true) {
        SuperHeroApp()
    }
}