Alarmio Remote
=======

Alarmio Remote is a fork of Alarmio that allows you to require a remote device to allow you to dismiss an alarm. This can help force you to get out of bed by requiring you to go to another phone and click the enable button.

See the [original repo](https://github.com/fennifith/Alarmio) for info about Alarmio itself.

# Usage

TODO: Make this part of the options page

- Change the `remoteDismissAuthCode` variable in `AlarmActivity.java`
- OPTIONAL: Change the line referencing `https://remotealarm.ajay.app/` in `AlarmActivity.java` to your own self-hosted version of [the server](https://github.com/ajayyy/AlarmioRemoteServer). This is not required and you can use my server if you'd like.
- OPTIONAL: Change `REMOTE_DISMISS` options in `PreferenceData.java`
- Setup another device to send a POST request to `https://remotealarm.ajay.app/api/v1/allowAlarmDismiss/[your-auth-code-goes-here]` or your own self-hosted server. This allows dismissing and snoozing for 5 minutes. I have this on an old phone in another room.
- Build and run the app on your device (see below if you have issues)

### Advanced options

You can send a POST request to `https://remotealarm.ajay.app/api/v1/allowAlarmDismiss/[your-auth-code-goes-here]/[custom-time-in-minutes]` to set a custom time to allow the alarm to be dismissed.

You can send a POST request to `https://remotealarm.ajay.app/api/v1/denyAlarmDismiss/[your-auth-code-goes-here]` to deny dismissing early.

In `PreferenceData.java`, changing `REMOTE_DISMISS_MAX_SNOOZES` allows you to allow a certain number of snoozes before requiring the other device to allow it.

## Permissions

- `SET_ALARM`, `VIBRATE`, `WAKE_LOCK`: uh, this should be obvious
- `ACCESS_COARSE_LOCATION`: determining automatic sunrise/sunset times for "scheduled" light/dark themes
- `INTERNET`: obtaining a set of sunrise/sunset times (location data is not shared outside of the device), as well as fetching graphical assets and some of the information in the about page
- `RECEIVE_BOOT_COMPLETED`: re-schedule alarms on startup
- `READ_EXTERNAL_STORAGE`: used to set custom background / header images in the settings
- `FOREGROUND_SERVICE`: used to notify you to sleep - see the "Sleep Reminder" option in the settings
- `REQUEST_IGNORE_BATTERY_OPTIMIZATIONS`: see [dontkillmyapp.com](https://dontkillmyapp.com/)

## Contributing & Build Instructions

Instructions for contributing to this project and building it locally can be found [here](./.github/CONTRIBUTING.md).
