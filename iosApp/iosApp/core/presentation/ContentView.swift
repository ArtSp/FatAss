import SwiftUI
import MPSwiftUI

struct ContentView: View {
    
    @Environment(\.appModule) var appModule
    
	var body: some View {
        CategoriesScreen(productsUseCase: appModule.productsUseCase)
    }
}

// MARK: - Preview

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView()
            .previewInNavigationView()
	}
}


