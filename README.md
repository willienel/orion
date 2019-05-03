### Instructions

Create a very basic mobile app with two buttons to query a JSON API.

1. Display the names of all the users using an alert when the first button is pressed.

2. Display the e-mail address of the person with username “Samantha” using an alert
when the second button is pressed.

**Note:** Both scenarios refer to https://jsonplaceholder.typicode.com/users

### Release

To build a release apk you need to add the following to your ~/.gradle/gradle.properties file

    ORION_RELEASE_STORE_FILE=<path_to_keystore>
    ORION_RELEASE_STORE_PASSWORD=<keystore_password>
    ORION_RELEASE_KEY_ALIAS=<keystore_alias>
    ORION_RELEASE_KEY_PASSWORD=<keystore_alias_password>

## License

    Copyright 2019 Willie Nel

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.