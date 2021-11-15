# Capture Android SDK
Sample application for Capture SDK

## Step to use the SDK as below mentioned:

#### 1. build.grade (project):

```kotlin
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


```kotlin
dependencies {
    'implementation 'io.surepass.sdk:capture-android-sdk:2.1.0'
}
```

#### 3. Inside Application:

```kotlin
import io.surepass.captureandroidsdk.ui.VerificationActivity

    fun startCaptureSDK(){

        val token = "TOKEN" //token is needed when using full verification
        val env = "PREPROD" //Set Enviroment as PREPROD or PROD
        val intent = Intent(this, VerificationActivity::class.java)
        intent.putExtra("token",token)
        intent.putExtra("env",env)
        intent.putExtra("pancard",true) //Options ["aadhaar","pancard","license","passport","voterid"]
        intent.putExtra("aadhaar",true)
        
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

### Handling the responses in callback

SurePass Capture Android SDK returns following response depending on the status of process.

**Note:** Only the **200 SUCCESS** status sent to `onActivityResult` function represents successful process. Rest, all the other responses are dispatched to the `onActivityResult` function are errors.

- #### 200 SUCCESS

Returns HTTP status of 200 OK, when the Capture process has been completed successfully.

```json
{
  "data": {
    "client_id": "CLIENT_ID",
    "data": [...]
  },
  "id_type": ""
  "status_code": 200,
  "message_code": "SUCCESS",
  "message": "Verification Successfully",
  "success": true
}
```

- #### 401 UNAUTHORIZED ACCESS

```json
{
  "data": {
    "client_id": "CLIENT_ID"
  },
  "id_type": ""
  "status_code": 401,
  "message_code": "UNAUTH_ACCESS",
  "message": "Invalid Token",
  "success": false
}
```
