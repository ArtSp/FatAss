
import MPSwiftUI

extension View {
    func localized() -> some View {
        self.modifier(BundleLocalizationModifier())
    }
}

private struct BundleLocalizationModifier: ViewModifier {
    
    @Preference(\.bundleLanguage) var bundleLanguage
    
    func body(
        content: Content
    ) -> some View {
        content
            .locale(Locale(identifier: bundleLanguage.language.langCode))
    }
}
