
import MPSwiftUI

extension View {
    
    func appModule(
        _ appModule: AppModule
    ) -> some View {
        self.environment(\.appModule, appModule)
    }
    
}

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

