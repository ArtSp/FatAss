
import SwiftUI
import MPSwiftUI

struct CategoriesScreen: View {
    
    @ObservedObject var viewModel: ViewModel
    
    @Environment(\.appModule) private var appModule
    @Environment(\.locale) private var locale
    @Preference(\.bundleLanguage) private var bundleLanguage
    
    init(
        productsUseCase: ProductsUseCase
    ) {
        viewModel = ViewModel(productsUseCase: productsUseCase)
    }

    func categoriesStack(
        categories: [CategoryItem]
    ) -> some View {
        VStack(alignment: .leading) {
            ForEach(categories) { it in
                Button(action: { viewModel.onEvent(event: .ChooseCategory(category: it)) }) {
                    HStack {
                        Text(it.displayName)
                        Spacer()
                        Image(systemName: "chevron.forward")
                    }
                    .foregroundColor(.accentViolet)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding()
                    .background(Color.lightBlueGrey)
                    .cornerRadius(8)
                }
            }
            
            Spacer()
        }
        .textStyle(.body)
        .padding()
        .scrollViewIfNeeded(.vertical)
    }
    
    var body: some View {
        ZStack {
            Unwrap(viewModel.categories) { categories in
                categoriesStack(categories: categories)
            } fallbackContent: {
                categoriesStack(categories: CategoryItem.placeholders(count: 5))
                    .shimmedAndRedacted()
                    .isHidden(viewModel.isLoading.isEmpty)
            }
        }
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                LanguageDropDown(
                    language: bundleLanguage
                ) { newLanguage in
                    bundleLanguage = newLanguage
                }
            }
        }
        .navigationTitle("categories.navigation.title".localized(for: locale))
        .alert(isPresented: Binding(
            get: { !viewModel.error.isNil },
            set: { _ in viewModel.onEvent(event: .ClearError()) })
        ) {
            Alert(title: Text(viewModel.error?.message ?? ""))
        }
        .navigation(isActive: Binding(
            get: { !viewModel.selectedCategory.isNil },
            set: { _ in viewModel.onEvent(event: .ChooseCategory(category: nil)) }
        )) {
            Unwrap(viewModel.selectedCategory) { category in
                ProductsScreen(category: category, productsUseCase: appModule.productsUseCase)
            }
        }
        .onAppear {
            viewModel.startObserving()
            viewModel.onEvent(event: .LoadContent())
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

struct LanguageDropDown: View {
    var language: UiLanguage
    var selectLanguage: (UiLanguage) -> Void
    
    var body: some View {
        Menu {
            VStack {
                ForEach(UiLanguage.companion.localizedLanguages, id: \.self.language.langCode) { language in
                    LanguageDropDownItem(
                        language: language,
                        onClick: { selectLanguage(language) }
                    )
                }
            }
        } label: {
            Unwrap(UIImage(named: language.language.langName.lowercased())) { uiImage in
                Image(uiImage: uiImage)
                    .resizable()
                    .frame(width: 22, height: 22)
                    .shadow(radius: 1)
            } fallbackContent: {
                Text(language.language.langName)
                    .foregroundColor(.accentViolet)
            }
        }
    }
}

struct LanguageDropDownItem: View {
    var language: UiLanguage
    var onClick: () -> Void
    var body: some View {
        Button(action: onClick) {
            HStack {
                Unwrap(UIImage(named: language.imageName.lowercased())) { image in
                    Image(uiImage: image)
                        .resizable()
                        .frame(width: 22, height: 22)
                        .padding(.trailing, 5)
                }
                Text(language.language.langName)
                    .foregroundColor(.textBlack)
            }
        }
        .buttonStyle(.plain)
    }
}

extension CategoryItem: Identifiable, PlaceholderProvider {
    static public var placeholder: CategoryItem { CategoryItem(id: RandomIdentifier, name: "Name") }
}

// MARK: - Preview

struct CategoriesScreen_Previews: PreviewProvider {
    
    static var previews: some View {
        CategoriesScreen(productsUseCase: AppModuleFake().productsUseCase)
            .previewInNavigationView()
    }
}
