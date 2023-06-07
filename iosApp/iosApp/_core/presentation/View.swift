
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
    
    func previewInNavigationView(
        localized: Bool = true
    ) -> some View {
        NavigationStack {
            self
        }.preview(localized: localized)
    }
    
    func localized() -> some View {
        self.modifier(BundleLocalizationModifier())
    }
    
    func appModule(
        _ appModule: AppModule
    ) -> some View {
        self.environment(\.appModule, appModule)
    }
    
}

// MARK: - EnvironmentValues

extension EnvironmentValues {
    /// Shows current flow sequence
    var appModule: AppModule {
        get { self[AppModuleKey.self] }
        set { self[AppModuleKey.self] = newValue }
    }
}

private struct AppModuleKey: EnvironmentKey {
    static let defaultValue: AppModule = AppModuleImpl()
}

// MARK: - BundleLocalizationModifier

private struct BundleLocalizationModifier: ViewModifier {
    
    @Preference(\.bundleLanguage) var bundleLanguage
    
    func body(
        content: Content
    ) -> some View {
        content
            .locale(Locale(identifier: bundleLanguage.language.langCode))
    }
}

