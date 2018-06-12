package com.example.ubuntu.appauthtest

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import net.openid.appauth.*

class MainActivity : AppCompatActivity() {

    val MY_CLIENT_ID = "302411502544.308844295345" //YOU WILL NEED TO HAVE A SLACK APP TO HAVE AN ID
    val MY_REDIRECT_URI = Uri.parse("https://example.com/app_auth") //SAMPLE REDIRECT URI
    val RC_AUTH = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO set authorization endpoint and token endpoint with Uri.parse
        val serviceConfig : AuthorizationServiceConfiguration = AuthorizationServiceConfiguration (
                Uri.parse("https://slack.com/oauth/authorize"),
                Uri.parse("https://slack.com/api/oauth.access")
        )

        //TODO set our request builder
        val authRequestBuilder : AuthorizationRequest.Builder = AuthorizationRequest.Builder(
                serviceConfig,
                MY_CLIENT_ID,
                ResponseTypeValues.CODE,
                MY_REDIRECT_URI)

        //TODO set our scope
        val authRequest : AuthorizationRequest = authRequestBuilder
                .setScope("identity.basic")
                .build()

        //TODO make authorization
        fun doAuthorization(){
            val authService : AuthorizationService = AuthorizationService(this)
            val authIntent : Intent = authService.getAuthorizationRequestIntent(authRequest)
            startActivityForResult(authIntent, RC_AUTH)

        }

        doAuthorization()

    }


    //TODO stuck here ???
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == RC_AUTH){
            // not sure what I'm doing
            //TODO understand the "!!" in this case
            val resp: AuthorizationResponse = AuthorizationResponse.fromIntent(data)!!
            val ex: AuthorizationException = AuthorizationException.fromIntent(data)!!

            if(resp != null){

                Toast.makeText(this,"authorization complete",Toast.LENGTH_LONG).show()

            }else{

                Toast.makeText(this, ex.printStackTrace().toString(),Toast.LENGTH_LONG).show()
            }

        }
    }

    //TODO AFTER TESTING, TROUBLE CAPTURING THE RESPONSE DOESN'T COME BACK FROM MY APP?????

}
