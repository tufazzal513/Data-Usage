/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
 */

import { Download } from 'lucide-react';

export default function App() {
  return (
    <div className="min-h-screen bg-neutral-950 text-neutral-50 flex flex-col items-center justify-center p-8 font-sans">
      <div className="max-w-md w-full bg-neutral-900 border border-neutral-800 rounded-2xl p-8 text-center space-y-6 shadow-2xl">
        <div className="bg-emerald-500/20 text-emerald-400 p-4 rounded-full inline-flex">
          <Download className="w-12 h-12" />
        </div>
        <div className="space-y-2">
          <h1 className="text-2xl font-bold">Android Project Ready</h1>
          <p className="text-neutral-400">
            Your native Kotlin Android app <strong>"Data Usage"</strong> has been generated.
            Because this is a native Android project, it cannot run directly in the browser preview.
          </p>
        </div>
        <div className="bg-neutral-800/50 rounded-lg p-4 text-sm text-left space-y-3">
          <p className="font-semibold text-neutral-300">How to run it:</p>
          <ol className="list-decimal list-inside text-neutral-400 space-y-2">
            <li>Click the menu (three dots) in the top right of AI Studio.</li>
            <li>Select <strong>Export to ZIP</strong>.</li>
            <li>Extract the ZIP and open the folder in <strong>Android Studio</strong>.</li>
            <li>Build and run on your emulator or Android device.</li>
          </ol>
        </div>
      </div>
    </div>
  );
}
