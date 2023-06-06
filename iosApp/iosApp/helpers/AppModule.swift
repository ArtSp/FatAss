
import SwiftUI

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

private struct FakeAppModuleModifier: ViewModifier {
    
    func body(
        content: Content
    ) -> some View {
        content.appModule(AppModuleFake())
    }
}

extension View {
    
    func appModule(
        _ appModule: AppModule
    ) -> some View {
        self.environment(\.appModule, appModule)
    }
    
    func preview() -> some View {
        modifier(FakeAppModuleModifier())
    }
    
    func previewInNavigationView() -> some View {
        NavigationStack {
            self
        }.preview()
    }
}


