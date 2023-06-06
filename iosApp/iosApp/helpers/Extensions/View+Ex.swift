
import MPSwiftUI

extension View {
    
    @ViewBuilder
    func preview(
        localized: Bool = true
    ) -> some View {
        Group {
            if localized {
                ForEach(UiLanguage.companion.localizedLanguages, id: \.self.language.langCode) { uiLanguage in
                    Unwrap(Locale(identifier: uiLanguage.language.langCode)) { locale in
                        self.locale(locale)
                    } fallbackContent: {
                        Text("Invalid locale preview")
                    }
                }
            } else {
                self
            }
        }
        .appModule(AppModuleFake())
    }
    
    @ViewBuilder
    func previewInNavigationView(
        localized: Bool = true
    ) -> some View {
        NavigationStack {
            self
        }.preview(localized: localized)
    }
    
}
