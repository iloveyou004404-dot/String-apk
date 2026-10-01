package com.example.data

import android.provider.Settings
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import com.example.model.InteractiveType
import com.example.model.SettingItem

object SettingsRepository {

    fun getAllSettings(): List<SettingItem> {
        val cyan = Color(0xFF00F2FE)
        val teal = Color(0xFF00F5D4)
        val purple = Color(0xFFB5179E)
        val blue = Color(0xFF4361EE)
        val amber = Color(0xFFFF9E00)
        val pink = Color(0xFFFF0054)
        val green = Color(0xFF38EF7D)

        return listOf(
            // ==================== A ====================
            SettingItem(
                id = "acc_accounts",
                letter = 'A',
                title = "Accounts",
                subtitle = "Google, Work profiles, Sync credentials",
                category = "Accounts & Identity",
                icon = Icons.Default.AccountCircle,
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_SYNC_SETTINGS,
                description = "Manage system accounts, automated synchronization, and cloud credentials."
            ),
            SettingItem(
                id = "acc_accessibility",
                letter = 'A',
                title = "Accessibility",
                subtitle = "TalkBack, Display scaling, Interaction controls",
                category = "Assistance",
                icon = Icons.Default.Accessibility,
                accentColor = green,
                androidIntentAction = Settings.ACTION_ACCESSIBILITY_SETTINGS,
                description = "Enhance readability, hearing assistance, motor interaction, and screen readers."
            ),
            SettingItem(
                id = "acc_airplane",
                letter = 'A',
                title = "Airplane Mode",
                subtitle = "Disable all radio transmissions instantly",
                category = "Network",
                icon = Icons.Default.AirplanemodeActive,
                hasToggle = true,
                defaultEnabled = false,
                accentColor = amber,
                androidIntentAction = Settings.ACTION_AIRPLANE_MODE_SETTINGS,
                description = "Quick toggle to suspend cellular, Wi-Fi, and Bluetooth broadcasts."
            ),
            SettingItem(
                id = "acc_apps",
                letter = 'A',
                title = "Apps",
                subtitle = "Default apps, Storage usage, App info",
                category = "Application Management",
                icon = Icons.Default.Apps,
                accentColor = blue,
                androidIntentAction = Settings.ACTION_APPLICATION_SETTINGS,
                description = "Browse all installed system & third-party applications and default handlers."
            ),
            SettingItem(
                id = "acc_permissions",
                letter = 'A',
                title = "App Permissions",
                subtitle = "Camera, Mic, Location access rules",
                category = "Application Management",
                icon = Icons.Default.Security,
                badgeText = "Protected",
                accentColor = purple,
                androidIntentAction = Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS,
                description = "Audit and revoke high-privilege device hardware permissions per application."
            ),

            // ==================== B ====================
            SettingItem(
                id = "bat_battery",
                letter = 'B',
                title = "Battery",
                subtitle = "Power modes, Health meter, Usage stats",
                category = "Power & Energy",
                icon = Icons.Default.BatteryChargingFull,
                badgeText = "88% • Normal",
                accentColor = green,
                androidIntentAction = Settings.ACTION_BATTERY_SAVER_SETTINGS,
                description = "Inspect real-time discharge curve, adaptive battery limits, and temperature monitoring."
            ),
            SettingItem(
                id = "bat_bluetooth",
                letter = 'B',
                title = "Bluetooth",
                subtitle = "LE Audio, Codecs, Paired peripherals",
                category = "Connectivity",
                icon = Icons.Default.Bluetooth,
                hasToggle = true,
                defaultEnabled = true,
                accentColor = blue,
                androidIntentAction = Settings.ACTION_BLUETOOTH_SETTINGS,
                description = "Fast pairing with high-definition LDAC/aptX earbuds and wearable watches."
            ),
            SettingItem(
                id = "bat_backup",
                letter = 'B',
                title = "Backup",
                subtitle = "Encrypted cloud snapshots & local restore",
                category = "System & Data",
                icon = Icons.Default.CloudUpload,
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_PRIVACY_SETTINGS,
                description = "Automatic daily zero-knowledge snapshots of messages, contacts, and app states."
            ),
            SettingItem(
                id = "bat_biometrics",
                letter = 'B',
                title = "Biometrics",
                subtitle = "Ultrasonic fingerprint, Face ID credentials",
                category = "Security",
                icon = Icons.Default.Fingerprint,
                badgeText = "Hardware Secure",
                accentColor = purple,
                androidIntentAction = Settings.ACTION_SECURITY_SETTINGS,
                description = "Configure sub-display ultrasonic scanner and 3D depth biometric security."
            ),

            // ==================== C ====================
            SettingItem(
                id = "call_calls",
                letter = 'C',
                title = "Calls",
                subtitle = "VoLTE, Wi-Fi calling, Spam screening",
                category = "Telephony",
                icon = Icons.Default.Call,
                accentColor = green,
                androidIntentAction = Settings.ACTION_NETWORK_OPERATOR_SETTINGS,
                description = "Smart call screening, carrier VoWiFi prioritization, and auto-record settings."
            ),
            SettingItem(
                id = "cam_camera",
                letter = 'C',
                title = "Camera",
                subtitle = "RAW capture, Grid lines, Hardware access",
                category = "Hardware",
                icon = Icons.Default.CameraAlt,
                accentColor = pink,
                description = "Optical stabilization modes, HDR10+ video, and hardware microphone routing."
            ),
            SettingItem(
                id = "con_connections",
                letter = 'C',
                title = "Connections",
                subtitle = "NFC, UWB, Nearby share, Tethering",
                category = "Connectivity",
                icon = Icons.Default.Share,
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_WIRELESS_SETTINGS,
                description = "Ultra-wideband device tracking, instant link sharing, and multi-device tethering."
            ),
            SettingItem(
                id = "cast_cast",
                letter = 'C',
                title = "Cast",
                subtitle = "Wireless display mirroring, Google Cast",
                category = "Media",
                icon = Icons.Default.Cast,
                accentColor = blue,
                androidIntentAction = Settings.ACTION_CAST_SETTINGS,
                description = "Stream video, 4K screen mirroring, and high-fidelity audio to living room screens."
            ),
            SettingItem(
                id = "cell_network",
                letter = 'C',
                title = "Cellular Network",
                subtitle = "5G SA/NSA, Roaming, APN profiles",
                category = "Network",
                icon = Icons.Default.SignalCellularAlt,
                badgeText = "5G Ultra",
                accentColor = teal,
                androidIntentAction = Settings.ACTION_NETWORK_OPERATOR_SETTINGS,
                description = "Configure dual-SIM failover, NR standalone bands, and data limits."
            ),

            // ==================== D ====================
            SettingItem(
                id = "disp_display",
                letter = 'D',
                title = "Display",
                subtitle = "120Hz LTPO, Brightness, Color calibration",
                category = "Display & Visuals",
                icon = Icons.Default.BrightnessMedium,
                interactiveType = InteractiveType.SLIDER,
                sliderValue = 0.78f,
                sliderLabel = "Brightness Level",
                accentColor = amber,
                androidIntentAction = Settings.ACTION_DISPLAY_SETTINGS,
                description = "Dynamic refresh rate 1-120Hz, HDR peak brightness, and true-tone color temps."
            ),
            SettingItem(
                id = "dt_datetime",
                letter = 'D',
                title = "Date & Time",
                subtitle = "Automatic NTP sync, Time zone, Dual clock",
                category = "System",
                icon = Icons.Default.Schedule,
                accentColor = blue,
                androidIntentAction = Settings.ACTION_DATE_SETTINGS,
                description = "Precision atomic network time protocol sync and world clock travel companion."
            ),
            SettingItem(
                id = "dw_wellbeing",
                letter = 'D',
                title = "Digital Wellbeing",
                subtitle = "Screen time tracking, App timers, Bedtime",
                category = "Health",
                icon = Icons.Default.Spa,
                badgeText = "3h 42m today",
                accentColor = green,
                description = "Daily application usage graphs, focus mode automations, and grayscale sleep modes."
            ),
            SettingItem(
                id = "dev_options",
                letter = 'D',
                title = "Developer Options",
                subtitle = "USB debugging, Window animation scales, GPU rendering",
                category = "Advanced",
                icon = Icons.Default.Code,
                badgeText = "Active",
                accentColor = purple,
                androidIntentAction = Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS,
                description = "Low-level system debugging, memory heap tracking, and frame rate counters."
            ),

            // ==================== E ====================
            SettingItem(
                id = "emg_sos",
                letter = 'E',
                title = "Emergency SOS",
                subtitle = "Rapid 5-press trigger, Medical ID broadcast",
                category = "Safety",
                icon = Icons.Default.Warning,
                badgeText = "Critical",
                accentColor = pink,
                description = "Auto-dial emergency responders, share live satellite coordinates, and flash strobe."
            ),
            SettingItem(
                id = "eml_email",
                letter = 'E',
                title = "Email",
                subtitle = "IMAP/Exchange push protocols, Notification priority",
                category = "Communication",
                icon = Icons.Default.Email,
                accentColor = blue,
                description = "Unified inbox synchronization, background fetching intervals, and attachment limits."
            ),
            SettingItem(
                id = "enc_encryption",
                letter = 'E',
                title = "Encryption",
                subtitle = "File-based AES-256 encryption & Knox Knox-grade key vault",
                category = "Security",
                icon = Icons.Default.Lock,
                badgeText = "Hardware Encrypted",
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_SECURITY_SETTINGS,
                description = "Full storage cryptographic partition status and hardware-backed keystore protection."
            ),

            // ==================== F ====================
            SettingItem(
                id = "fp_fingerprint",
                letter = 'F',
                title = "Fingerprint",
                subtitle = "Ultrasonic scanner enrollment, Fast wake",
                category = "Biometrics",
                icon = Icons.Default.Fingerprint,
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_SECURITY_SETTINGS,
                description = "Register up to 5 fingerprint maps with 0.18s unlock latency."
            ),
            SettingItem(
                id = "face_unlock",
                letter = 'F',
                title = "Face Unlock",
                subtitle = "3D infrared depth biometric mapping",
                category = "Biometrics",
                icon = Icons.Default.Face,
                accentColor = teal,
                androidIntentAction = Settings.ACTION_SECURITY_SETTINGS,
                description = "Require open eyes, anti-spoof liveness check, and low-light IR illumination."
            ),
            SettingItem(
                id = "fact_reset",
                letter = 'F',
                title = "Factory Reset",
                subtitle = "Wipe user data, Erase eSIM credentials",
                category = "System Maintenance",
                icon = Icons.Default.DeleteSweep,
                accentColor = pink,
                androidIntentAction = Settings.ACTION_PRIVACY_SETTINGS,
                description = "Completely purge flash storage and return phone to original factory pristine state."
            ),
            SettingItem(
                id = "file_manager",
                letter = 'F',
                title = "File Manager",
                subtitle = "Storage explorer, Trash bin, Fast transfer",
                category = "Storage",
                icon = Icons.Default.Folder,
                accentColor = amber,
                description = "Inspect downloads, documents, APK packages, and compressed archives."
            ),

            // ==================== G ====================
            SettingItem(
                id = "g_google",
                letter = 'G',
                title = "Google Settings",
                subtitle = "Play Protect, Autofill, Find My Device",
                category = "Google Services",
                icon = Icons.Default.Search,
                accentColor = blue,
                description = "Configure Google ecosystem sync, password manager, and emergency location alerts."
            ),
            SettingItem(
                id = "g_gestures",
                letter = 'G',
                title = "Gestures",
                subtitle = "Double tap to sleep, Quick launch camera, 3-finger swipe",
                category = "Navigation & Inputs",
                icon = Icons.Default.TouchApp,
                accentColor = cyan,
                description = "Full edge swipe navigation gestures, back swipe sensitivity, and gesture bar hiding."
            ),
            SettingItem(
                id = "g_gps",
                letter = 'G',
                title = "GPS",
                subtitle = "Dual-frequency L1+L5 satellite positioning",
                category = "Location",
                icon = Icons.Default.GpsFixed,
                hasToggle = true,
                defaultEnabled = true,
                accentColor = green,
                androidIntentAction = Settings.ACTION_LOCATION_SOURCE_SETTINGS,
                description = "Ultra-precision outdoor location with Galileo, GLONASS, and BeiDou integration."
            ),
            SettingItem(
                id = "g_gaming",
                letter = 'G',
                title = "Gaming Mode",
                subtitle = "GPU overclock, Notification silencer, Touch boost",
                category = "Performance",
                icon = Icons.Default.SportsEsports,
                hasToggle = true,
                defaultEnabled = false,
                accentColor = pink,
                description = "Lock 120 FPS frame targets, allocate maximal thermal headroom, and suppress banners."
            ),

            // ==================== H ====================
            SettingItem(
                id = "h_home",
                letter = 'H',
                title = "Home Screen",
                subtitle = "Grid layout, Icon packs, At-a-Glance widgets",
                category = "Customization",
                icon = Icons.Default.Home,
                accentColor = amber,
                description = "Configure drawer style, transition animations, and notification count badges."
            ),
            SettingItem(
                id = "h_hotspot",
                letter = 'H',
                title = "Hotspot",
                subtitle = "Wi-Fi 6 / 6GHz band sharing & USB tethering",
                category = "Network",
                icon = Icons.Default.WifiTethering,
                hasToggle = true,
                defaultEnabled = false,
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_WIRELESS_SETTINGS,
                description = "Broadcast encrypted high-speed personal Wi-Fi hotspot with automatic timeout."
            ),
            SettingItem(
                id = "h_haptic",
                letter = 'H',
                title = "Haptic Feedback",
                subtitle = "Tactile linear motor vibration crispness",
                category = "Audio & Vibration",
                icon = Icons.Default.Vibration,
                hasToggle = true,
                defaultEnabled = true,
                accentColor = purple,
                description = "Fine-tune click sensations for keyboard taps, gesture boundaries, and volume adjustments."
            ),

            // ==================== I ====================
            SettingItem(
                id = "i_internet",
                letter = 'I',
                title = "Internet",
                subtitle = "DNS over HTTPS, Carrier data, Proxy",
                category = "Network",
                icon = Icons.Default.Language,
                accentColor = blue,
                androidIntentAction = Settings.ACTION_WIFI_SETTINGS,
                description = "Manage encrypted DNS providers (Cloudflare, Quad9) and cellular fallbacks."
            ),
            SettingItem(
                id = "i_installed_apps",
                letter = 'I',
                title = "Installed Apps",
                subtitle = "Battery optimization rules, Cache cleaning",
                category = "Applications",
                icon = Icons.Default.Layers,
                accentColor = teal,
                androidIntentAction = Settings.ACTION_APPLICATION_SETTINGS,
                description = "Inspect space occupied by system APKs, caches, and unused dormant applications."
            ),
            SettingItem(
                id = "i_input",
                letter = 'I',
                title = "Input",
                subtitle = "Keyboard pointers, Stylus pressure, Dictation",
                category = "Inputs",
                icon = Icons.Default.Keyboard,
                accentColor = amber,
                androidIntentAction = Settings.ACTION_INPUT_METHOD_SETTINGS,
                description = "Configure input methods, stylus palm rejection, and offline voice transcription."
            ),
            SettingItem(
                id = "i_intel",
                letter = 'I',
                title = "Intelligent Features",
                subtitle = "Smart scene detection, Auto brightness learning",
                category = "System Intelligence",
                icon = Icons.Default.AutoAwesome,
                badgeText = "Neural Engine",
                accentColor = cyan,
                description = "On-device machine learning for app pre-loading, smart rotation, and charging habits."
            ),

            // ==================== J ====================
            SettingItem(
                id = "j_lang_input",
                letter = 'J',
                title = "Languages & Input",
                subtitle = "Multilingual keyboards, Spell checking, TTS voice",
                category = "Localization",
                icon = Icons.Default.Translate,
                accentColor = green,
                androidIntentAction = Settings.ACTION_INPUT_METHOD_SETTINGS,
                description = "Configure multi-lingual typing dictionaries, speech synthesis engines, and handwriting."
            ),

            // ==================== K ====================
            SettingItem(
                id = "k_keyboard",
                letter = 'K',
                title = "Keyboard",
                subtitle = "Haptic intensity, Glide typing, Clipboard history",
                category = "Inputs",
                icon = Icons.Default.KeyboardAlt,
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_INPUT_METHOD_SETTINGS,
                description = "Customize on-screen keyboard theme, height, row numbers, and smart text correction."
            ),
            SettingItem(
                id = "k_keypad",
                letter = 'K',
                title = "Keypad",
                subtitle = "Dialer DTMF tones, Emergency keypad styling",
                category = "Telephony",
                icon = Icons.Default.Dialpad,
                accentColor = blue,
                description = "Phone keypad response sounds, speed-dial shortcuts, and accessibility keypad tones."
            ),

            // ==================== L ====================
            SettingItem(
                id = "l_language",
                letter = 'L',
                title = "Language",
                subtitle = "System UI language, Regional formatting",
                category = "Localization",
                icon = Icons.Default.Language,
                badgeText = "English (US)",
                accentColor = teal,
                androidIntentAction = Settings.ACTION_LOCALE_SETTINGS,
                description = "Set primary interface language and per-app language preferences."
            ),
            SettingItem(
                id = "l_location",
                letter = 'L',
                title = "Location",
                subtitle = "High accuracy Wi-Fi scanning, Geofence permissions",
                category = "Privacy & Location",
                icon = Icons.Default.LocationOn,
                hasToggle = true,
                defaultEnabled = true,
                accentColor = amber,
                androidIntentAction = Settings.ACTION_LOCATION_SOURCE_SETTINGS,
                description = "Review apps recently accessing location and toggle approximate vs precise GPS."
            ),
            SettingItem(
                id = "l_lockscreen",
                letter = 'L',
                title = "Lock Screen",
                subtitle = "Always On Display, Clock typography, Smart widgets",
                category = "Display",
                icon = Icons.Default.LockClock,
                accentColor = purple,
                description = "Customize ambient AOD clock face, notification privacy on lock, and shortcut icons."
            ),

            // ==================== M ====================
            SettingItem(
                id = "m_mobile_network",
                letter = 'M',
                title = "Mobile Network",
                subtitle = "SIM slot 1 & 2, VoNR, Network search",
                category = "Cellular",
                icon = Icons.Default.CellTower,
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_NETWORK_OPERATOR_SETTINGS,
                description = "Preferred network types (5G/LTE/3G), manual operator search, and roaming agreements."
            ),
            SettingItem(
                id = "m_mobile_data",
                letter = 'M',
                title = "Mobile Data",
                subtitle = "Monthly quota, Data saver mode, Warning limits",
                category = "Cellular",
                icon = Icons.Default.DataUsage,
                hasToggle = true,
                defaultEnabled = true,
                accentColor = green,
                androidIntentAction = Settings.ACTION_NETWORK_OPERATOR_SETTINGS,
                description = "Restrict background data consumption and configure rollover billing cycles."
            ),
            SettingItem(
                id = "m_memory",
                letter = 'M',
                title = "Memory",
                subtitle = "16 GB LPDDR5X + 8 GB Liquid RAM expansion",
                category = "Performance",
                icon = Icons.Default.Memory,
                badgeText = "6.2 GB Free",
                accentColor = purple,
                description = "Inspect memory usage by active background daemons and clear memory leaks."
            ),
            SettingItem(
                id = "m_messaging",
                letter = 'M',
                title = "Messaging",
                subtitle = "RCS Chat features, End-to-end encryption, MMS",
                category = "Communication",
                icon = Icons.Default.Message,
                accentColor = blue,
                description = "Rich Communication Services status, auto-download media over mobile, and spam filters."
            ),

            // ==================== N ====================
            SettingItem(
                id = "n_notifications",
                letter = 'N',
                title = "Notifications",
                subtitle = "Heads-up popups, Bubbles, Notification history",
                category = "System Alerts",
                icon = Icons.Default.Notifications,
                accentColor = amber,
                androidIntentAction = Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS,
                description = "Configure snooze intervals, lock screen previews, and persistent notification priority."
            ),
            SettingItem(
                id = "n_nfc",
                letter = 'N',
                title = "NFC",
                subtitle = "Contactless Google Pay, Tag reader",
                category = "Connectivity",
                icon = Icons.Default.Nfc,
                hasToggle = true,
                defaultEnabled = true,
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_NFC_SETTINGS,
                description = "Near Field Communication for tap-to-pay terminals and smart transit cards."
            ),
            SettingItem(
                id = "n_nav_bar",
                letter = 'N',
                title = "Navigation Bar",
                subtitle = "Swipe gesture navigation vs 3-button layout",
                category = "Interface",
                icon = Icons.Default.SmartButton,
                accentColor = pink,
                description = "Toggle gesture navigation bar pill visibility and button ordering (Back-Home-Recents)."
            ),

            // ==================== O ====================
            SettingItem(
                id = "o_one_handed",
                letter = 'O',
                title = "One-handed Mode",
                subtitle = "Pull down top of screen to reach controls",
                category = "Ergonomics",
                icon = Icons.Default.PanTool,
                hasToggle = true,
                defaultEnabled = false,
                accentColor = teal,
                description = "Shift the upper half of screen content downward for seamless single-handed navigation."
            ),
            SettingItem(
                id = "o_otg",
                letter = 'O',
                title = "OTG",
                subtitle = "USB On-The-Go host storage & peripheral connection",
                category = "Hardware",
                icon = Icons.Default.Usb,
                hasToggle = true,
                defaultEnabled = true,
                accentColor = cyan,
                description = "Power external flash drives, DAC audio converters, and keyboards via Type-C."
            ),
            SettingItem(
                id = "o_os",
                letter = 'O',
                title = "Operating System",
                subtitle = "LiquidOS v4.2 • Android 16 Core",
                category = "System Specs",
                icon = Icons.Default.Android,
                badgeText = "Up to date",
                accentColor = green,
                androidIntentAction = Settings.ACTION_DEVICE_INFO_SETTINGS,
                description = "Inspect build kernel 6.6-android, security patch date, and compilation branch."
            ),

            // ==================== P ====================
            SettingItem(
                id = "p_privacy",
                letter = 'P',
                title = "Privacy",
                subtitle = "Permission manager, Camera/mic indicator pills",
                category = "Privacy",
                icon = Icons.Default.PrivacyTip,
                badgeText = "Active Guard",
                accentColor = green,
                androidIntentAction = Settings.ACTION_PRIVACY_SETTINGS,
                description = "Global hardware kill switches for camera, microphone, and clipboard read warnings."
            ),
            SettingItem(
                id = "p_permissions",
                letter = 'P',
                title = "Permissions",
                subtitle = "Sensors, Contacts, Storage, Nearby devices",
                category = "Privacy",
                icon = Icons.Default.AdminPanelSettings,
                accentColor = purple,
                androidIntentAction = Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS,
                description = "Detailed matrix of device capabilities granted to installed applications."
            ),
            SettingItem(
                id = "p_passwords",
                letter = 'P',
                title = "Passwords",
                subtitle = "Autofill service, Passkeys, Biometric lock",
                category = "Security",
                icon = Icons.Default.Key,
                accentColor = amber,
                description = "Manage FIDO2 passkeys, auto-fill credentials, and master passcodes."
            ),
            SettingItem(
                id = "p_power_saving",
                letter = 'P',
                title = "Power Saving",
                subtitle = "Ultra battery stamina, 60Hz lock, Dark UI",
                category = "Power",
                icon = Icons.Default.PowerSettingsNew,
                hasToggle = true,
                defaultEnabled = false,
                accentColor = pink,
                androidIntentAction = Settings.ACTION_BATTERY_SAVER_SETTINGS,
                description = "Extend remaining run-time up to 48 hours by curtailing background tasks."
            ),

            // ==================== Q ====================
            SettingItem(
                id = "q_quick_settings",
                letter = 'Q',
                title = "Quick Settings",
                subtitle = "Tile order, Brightness slider placement, Media player",
                category = "Interface",
                icon = Icons.Default.Tune,
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_QUICK_LAUNCH_SETTINGS,
                description = "Customize notification shade quick toggles, layout density, and smart device controls."
            ),
            SettingItem(
                id = "q_qr_scanner",
                letter = 'Q',
                title = "QR Scanner",
                subtitle = "Instant lens scanner for Wi-Fi, URLs, Contacts",
                category = "Tools",
                icon = Icons.Default.QrCodeScanner,
                interactiveType = InteractiveType.QR_SCANNER,
                badgeText = "Camera Ready",
                accentColor = teal,
                description = "Fast optical scanner to join Wi-Fi networks and decode 2D barcodes."
            ),

            // ==================== R ====================
            SettingItem(
                id = "r_reset_options",
                letter = 'R',
                title = "Reset Options",
                subtitle = "Reset Wi-Fi & Bluetooth, Reset app preferences",
                category = "System Maintenance",
                icon = Icons.Default.Restore,
                accentColor = pink,
                androidIntentAction = Settings.ACTION_PRIVACY_SETTINGS,
                description = "Safely reset network stacks or application permissions without erasing personal files."
            ),
            SettingItem(
                id = "r_ringtone",
                letter = 'R',
                title = "Ringtone",
                subtitle = "Liquid Chimes (Custom Hi-Res audio)",
                category = "Audio",
                icon = Icons.Default.MusicNote,
                accentColor = purple,
                androidIntentAction = Settings.ACTION_SOUND_SETTINGS,
                description = "Select incoming call tones, notification acoustic chimes, and alarm audio."
            ),
            SettingItem(
                id = "r_ram",
                letter = 'R',
                title = "RAM",
                subtitle = "LPDDR5X 16GB • Active ZRAM compression",
                category = "Performance",
                icon = Icons.Default.DeveloperBoard,
                badgeText = "64% utilized",
                accentColor = cyan,
                description = "Dynamic kernel zRAM compression and smart application hibernation policies."
            ),

            // ==================== S ====================
            SettingItem(
                id = "s_security",
                letter = 'S',
                title = "Security",
                subtitle = "Play Protect, Knox Vault, Patch updates",
                category = "Security",
                icon = Icons.Default.Shield,
                badgeText = "Protected",
                accentColor = green,
                androidIntentAction = Settings.ACTION_SECURITY_SETTINGS,
                description = "Comprehensive real-time device integrity status and Google Play Protect checks."
            ),
            SettingItem(
                id = "s_sound",
                letter = 'S',
                title = "Sound",
                subtitle = "Dolby Atmos, EQ presets, Volume limits",
                category = "Audio",
                icon = Icons.AutoMirrored.Filled.VolumeUp,
                interactiveType = InteractiveType.SLIDER,
                sliderValue = 0.65f,
                sliderLabel = "Media Volume",
                accentColor = blue,
                androidIntentAction = Settings.ACTION_SOUND_SETTINGS,
                description = "Fine-tune 10-band spatial audio equalizer, hearing profiles, and speaker balance."
            ),
            SettingItem(
                id = "s_storage",
                letter = 'S',
                title = "Storage",
                subtitle = "256 GB UFS 4.0 (148 GB available)",
                category = "Storage",
                icon = Icons.Default.Storage,
                badgeText = "42% Used",
                accentColor = amber,
                androidIntentAction = Settings.ACTION_INTERNAL_STORAGE_SETTINGS,
                description = "Browse system images, high-res photos, offline downloads, and system dump files."
            ),
            SettingItem(
                id = "s_screen",
                letter = 'S',
                title = "Screen",
                subtitle = "Resolution QHD+, Color gamut, Screen timeout",
                category = "Display",
                icon = Icons.Default.Screenshot,
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_DISPLAY_SETTINGS,
                description = "Switch between FHD+ and QHD+ native resolution, set ambient sleep timeouts."
            ),
            SettingItem(
                id = "s_sim",
                letter = 'S',
                title = "SIM",
                subtitle = "eSIM Profile 1 (Active) & Physical Nano-SIM",
                category = "Cellular",
                icon = Icons.Default.SimCard,
                badgeText = "Dual Active",
                accentColor = teal,
                androidIntentAction = Settings.ACTION_NETWORK_OPERATOR_SETTINGS,
                description = "Manage embedded eSIM profiles, data switching on secondary calls, and PIN locks."
            ),
            SettingItem(
                id = "s_software_update",
                letter = 'S',
                title = "Software Update",
                subtitle = "Check OTA servers for latest system build",
                category = "System Updates",
                icon = Icons.Default.SystemUpdate,
                badgeText = "Latest",
                accentColor = green,
                androidIntentAction = Settings.ACTION_DEVICE_INFO_SETTINGS,
                description = "Over-The-Air seamless background updates with A/B slot system partitioning."
            ),
            SettingItem(
                id = "s_system",
                letter = 'S',
                title = "System",
                subtitle = "System navigation, Multiple users, Timezones",
                category = "System Core",
                icon = Icons.Default.Settings,
                accentColor = blue,
                androidIntentAction = Settings.ACTION_SETTINGS,
                description = "Core device configurations, hardware diagnostics, and Android runtime settings."
            ),

            // ==================== T ====================
            SettingItem(
                id = "t_themes",
                letter = 'T',
                title = "Themes",
                subtitle = "Liquid Glass styles, Dynamic palette, Wallpapers",
                category = "Aesthetics",
                icon = Icons.Default.Palette,
                accentColor = purple,
                description = "Customize translucent glass UI accents, specular highlights, and system icon shapes."
            ),
            SettingItem(
                id = "t_touch_sensitivity",
                letter = 'T',
                title = "Touch Sensitivity",
                subtitle = "Increase screen sensitivity for glass protectors",
                category = "Inputs",
                icon = Icons.Default.TouchApp,
                hasToggle = true,
                defaultEnabled = false,
                accentColor = cyan,
                description = "Boost digitizer touch polling to 480Hz for flawless response through thick glass."
            ),
            SettingItem(
                id = "t_tts",
                letter = 'T',
                title = "Text-to-Speech",
                subtitle = "Natural neural voice engine & pitch controls",
                category = "Accessibility",
                icon = Icons.Default.RecordVoiceOver,
                accentColor = green,
                description = "Select on-device neural voice models, speech rate, and intonation pitch."
            ),

            // ==================== U ====================
            SettingItem(
                id = "u_usb",
                letter = 'U',
                title = "USB Settings",
                subtitle = "Default USB mode (File Transfer / MIDI / Tether)",
                category = "Hardware & I/O",
                icon = Icons.Default.Usb,
                accentColor = cyan,
                description = "Default behavior when plugged into computer, DisplayPort Alt mode, and audio routing."
            ),
            SettingItem(
                id = "u_updates",
                letter = 'U',
                title = "Updates",
                subtitle = "Google Play system update & Security bulletins",
                category = "System Updates",
                icon = Icons.Default.Sync,
                badgeText = "Current",
                accentColor = teal,
                androidIntentAction = Settings.ACTION_DEVICE_INFO_SETTINGS,
                description = "Modular Mainline APEX system updates and automated overnight installation."
            ),
            SettingItem(
                id = "u_user_accounts",
                letter = 'U',
                title = "User Accounts",
                subtitle = "Multi-user sandbox, Guest mode profiles",
                category = "Accounts",
                icon = Icons.Default.SupervisorAccount,
                accentColor = blue,
                description = "Isolated guest spaces with separate app installations and restricted calls."
            ),

            // ==================== V ====================
            SettingItem(
                id = "v_vibration",
                letter = 'V',
                title = "Vibration",
                subtitle = "Call vibration patterns, Notification haptics",
                category = "Audio & Haptics",
                icon = Icons.Default.Vibration,
                interactiveType = InteractiveType.SLIDER,
                sliderValue = 0.85f,
                sliderLabel = "Haptic Strength",
                accentColor = purple,
                androidIntentAction = Settings.ACTION_SOUND_SETTINGS,
                description = "Select rhythm patterns for VIP contacts and adjust feedback amplitude."
            ),
            SettingItem(
                id = "v_vpn",
                letter = 'V',
                title = "VPN",
                subtitle = "WireGuard & IKEv2 encrypted tunnels",
                category = "Network Security",
                icon = Icons.Default.VpnKey,
                badgeText = "Encrypted",
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_VPN_SETTINGS,
                description = "Always-on VPN profiles, kill switch to block unencrypted traffic, and bypass rules."
            ),
            SettingItem(
                id = "v_volume",
                letter = 'V',
                title = "Volume",
                subtitle = "Media, Ringtone, Notifications, Alarms",
                category = "Audio",
                icon = Icons.AutoMirrored.Filled.VolumeUp,
                interactiveType = InteractiveType.SLIDER,
                sliderValue = 0.70f,
                sliderLabel = "System Volume",
                accentColor = amber,
                androidIntentAction = Settings.ACTION_SOUND_SETTINGS,
                description = "Independent volume sliders and safe listening headphone ear protection alerts."
            ),

            // ==================== W ====================
            SettingItem(
                id = "w_wifi",
                letter = 'W',
                title = "Wi-Fi",
                subtitle = "Wi-Fi 7 (802.11be) • 5.8 Gbps tri-band link",
                category = "Network",
                icon = Icons.Default.Wifi,
                hasToggle = true,
                defaultEnabled = true,
                badgeText = "Connected",
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_WIFI_SETTINGS,
                description = "Connect to ultra-fast 2.4GHz, 5GHz, and 6GHz mesh networks with WPA3 enterprise."
            ),
            SettingItem(
                id = "w_wallpaper",
                letter = 'W',
                title = "Wallpaper",
                subtitle = "Liquid 3D interactive mesh & Cinematic depth",
                category = "Customization",
                icon = Icons.Default.Wallpaper,
                accentColor = purple,
                description = "Dynamic live wallpapers that react to device gyroscope and liquid ambient light."
            ),
            SettingItem(
                id = "w_widgets",
                letter = 'W',
                title = "Widgets",
                subtitle = "Liquid glass cards, System monitor pills",
                category = "Customization",
                icon = Icons.Default.Widgets,
                accentColor = amber,
                description = "Glassmorphic home screen widgets for battery stats, weather, and quick toggles."
            ),

            // ==================== X ====================
            SettingItem(
                id = "x_advanced",
                letter = 'X',
                title = "Advanced System Options",
                subtitle = "Kernel flags, Thermal throttling limits, Vulkan 1.3",
                category = "System Architecture",
                icon = Icons.Default.Terminal,
                badgeText = "Pro Mode",
                accentColor = pink,
                androidIntentAction = Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS,
                description = "Direct hardware kernel parameters, memory swap sizing, and graphics driver selection."
            ),

            // ==================== Y ====================
            SettingItem(
                id = "y_google_youtube",
                letter = 'Y',
                title = "Google / YouTube Settings",
                subtitle = "Picture-in-picture, Premium playback, Cast link",
                category = "Media Services",
                icon = Icons.Default.PlayCircle,
                accentColor = pink,
                description = "Configure default video resolution, background playback, and connected family links."
            ),

            // ==================== Z ====================
            SettingItem(
                id = "z_zoom",
                letter = 'Z',
                title = "Zoom",
                subtitle = "Display scale, Font size magnification",
                category = "Display & Accessibility",
                icon = Icons.Default.ZoomIn,
                interactiveType = InteractiveType.SLIDER,
                sliderValue = 0.50f,
                sliderLabel = "Magnification Level",
                accentColor = green,
                androidIntentAction = Settings.ACTION_ACCESSIBILITY_SETTINGS,
                description = "Adjust the DPI density scale to make text, icons, and menus larger and more legible."
            ),
            SettingItem(
                id = "z_magnification",
                letter = 'Z',
                title = "Magnification",
                subtitle = "Triple-tap full screen loupe tool",
                category = "Accessibility",
                icon = Icons.Default.Search,
                hasToggle = true,
                defaultEnabled = false,
                accentColor = amber,
                androidIntentAction = Settings.ACTION_ACCESSIBILITY_SETTINGS,
                description = "Quickly zoom into any part of the screen with a shortcut button or gestures."
            ),
            SettingItem(
                id = "z_accessibility_shortcuts",
                letter = 'Z',
                title = "Accessibility Shortcuts",
                subtitle = "Hardware volume keys long-press trigger",
                category = "Accessibility",
                icon = Icons.Default.AccessibilityNew,
                accentColor = cyan,
                androidIntentAction = Settings.ACTION_ACCESSIBILITY_SETTINGS,
                description = "Hold both volume keys for 3 seconds to toggle vision and hearing tools on the fly."
            )
        )
    }
}
