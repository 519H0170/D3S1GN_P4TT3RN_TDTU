# D3S1GN_P4TT3RN_TDTU
Group10 T5CA1

## How to use
Use Android studio software to run the program.
Actions with the participation of the design pattern are SignUpAct, LogInAct, CreateTruyen, TruyenChiTiet
To run Actions to the file AndroidManifest.xml:
		Run:
			android:name=".TruyenChiTiet"
			android:exported="true">
			<intent-filter>
				<action android:name="android.intent.action.MAIN" />

				<category android:name="android.intent.category.LAUNCHER" />
			not Run:
			android:name=".SignUpAct"
			android:exported="false">
			<meta-data
				android:name="android.app.lib_name"
				android:value="" />
