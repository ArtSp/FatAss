import SwiftUI
import MPSwiftUI

struct ContentView: View {
    
	var body: some View {
        CategoriesScreen(productsUseCase: AppModule().productsUseCase)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
