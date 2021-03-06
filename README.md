# capture-android-sdk-sample-app
Sample application for Capture SDK

Step to use the SDK below as well:

#### 1. build.grade (project):

```
allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven {
            url "https://maven.pkg.github.com/surepassio/capture-android-sdk-sample-app"
            credentials {
                username = "GITHUB_USER_NAME"
                password = "GITHUB_PAT_TOKEN" //https://docs.github.com/en/github/authenticating-to-github/keeping-your-account-and-data-secure/creating-a-personal-access-token (Allow Package Install Permission)
            }
        }
    }
}
```

#### 2. build.grade (app):


```
dependencies {
    'implementation 'io.surepass.sdk:capture-android-sdk:1.0.2'
}
```

#### 3. Inside Application:

```
import io.surepass.captureandroidsdk.ui.VerificationActivity

    fun startCaptureSDK(){

        val token = "TOKEN" //token is needed when using full verification
        val env = "TEST" //Set Enviroment as TEST or PROD

        val intent = Intent(this, VerificationActivity::class.java)
        intent.putExtra("token",token)
        intent.putExtra("env",env)
        intent.putExtra("pancard",true) //ID Options ["aadhaar","pancard","license","passport","voterid"]
        
        startActivityForResult(
            intent,
            10000
        )
    }



   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            10000 -> {
                val response = data?.getStringExtra("response");
                Log.d(CONSOLE, "response " + response)
                response?.let { setResponseOnTextView(it) }
            }
        }
    }
```
