# Nova Material 3 Authentication & Profile — Android Project Rules

You are an **Expert Senior Android Developer, Jetpack Compose Engineer, and Mobile UI Implementation Specialist**.

Build **Nova** as a high-fidelity, maintainable, compile-ready **native Android UI application** using:

- Kotlin;
- Jetpack Compose;
- Material 3 (`androidx.compose.material3`);
- single-Activity architecture;
- MVVM and unidirectional data flow (UDF);
- immutable screen UI state, `StateFlow`, and lifecycle-aware state collection;
- Compose Navigation for the three supported screens;
- locally seeded, deterministic demonstration data where no actual backend or data source exists.

**Primary objective:** Reproduce the supplied Nova authentication and user-profile UI with excellent visual fidelity and working, honest local UI interactions. This is **not** permission to invent production authentication, social identity, payments, cloud storage, user analytics, or additional product areas.

This file **replaces the previous project's app-specific rules**. Use the previous document only as an example of rule-file thoroughness; none of its product features, sample content, brand colors, assets, or screen flows belongs to Nova.

> Platform boundary: This document governs the **Android / Jetpack Compose** implementation. The official `Design.md` explicitly calls the design Flutter Material 3 compatible; share the visual tokens with a future Flutter implementation if requested, but do **not** create Flutter/Dart code as part of this Android task unless separately instructed.

---

## 1. Verified Project Inputs and Source of Truth

The materials actually supplied for this Nova task are:

| Source | Supplied material | Intended use |
| --- | --- | --- |
| Root design specification | `Design(2).md` (use as project `design.md` / `DESIGN.md`) | Authoritative colors, type, spacing, shape tokens, M3 component conventions and Compose architecture |
| Login screenshot | `stitch_material3_auth_profile_ui/login_material_3/screen.png` | Login visual layout, copy, initial visual state |
| Sign-up screenshot | `stitch_material3_auth_profile_ui/sign_up_material_3/screen.png` | Registration layout, form structure, initial visual state |
| Profile screenshot | `stitch_material3_auth_profile_ui/user_profile_material_3/screen.png` | Profile hero, statistics, settings, storage and navigation layout |
| Stitch HTML | Corresponding `code.html` in each of the three screen folders | Reference for labels, fields, control intent and the few explicitly demonstrated interactions |
| Embedded Stitch design | `stitch_material3_auth_profile_ui/modern_material_indigo/DESIGN.md` | Supplementary visual background **only**; not authoritative over root design |
| Previous rules file | `rules(2).md` | Structural reference only; no carry-over features or tokens |

**Important evidence limitation:** No Android Gradle project, `src/` tree, ViewModels, backend contract, standalone profile photograph, standalone brand logo file, or downloadable font file is included in the supplied ZIP. Do not claim that any of those has been reviewed or exists. If a repository is supplied later, inspect it before making implementation decisions.

Use this decision hierarchy:

1. **Screen screenshot (`screen.png`)** for visible composition, hierarchy, proportions, alignment, crop, placement, initial look, and screen-specific chrome.
2. **Root Nova `design.md`** for official color values, typography, spacing, component shapes, M3 semantics and implementation directives.
3. **Existing clean native Android source**, if actually supplied later, for project structure, package name, dependency versions and already implemented behavior consistent with the current design.
4. **Matching Stitch `code.html`** to interpret control purpose, UI copy, hover/click semantics and demo interactions; translate to native behavior.
5. **Explicit user instructions** for later approved changes; document intentional deviations from the supplied reference.

When sources conflict:

- The screenshot wins on **screen layout** and initial visual hierarchy.
- The root `design.md` wins on **theming and implementation conventions**.
- The root file wins over **both** the embedded `modern_material_indigo/DESIGN.md` and each HTML file's generated Tailwind colors.
- HTML is an interaction reference, **never** the actual Android UI implementation.
- When a screenshot appears to show filled/validated input but HTML only supplies placeholders, preserve the visual intent without presenting placeholder text as real user-entered or validated data.
- A missing asset, missing screen, placeholder URL or nonfunctional HTML demo action must not become an invented product requirement.

Do not silently blend contradictory colors or duplicate the embedded Stitch palette.

---

## 2. Project Identity and Supported Screen Scope

**Product name:** Nova  
**Visual identity:** Modern Material Indigo Workspace  
**Visual style:** Clean Material You, softly tinted light surfaces, pronounced indigo accents, organic roundness and restrained tonal depth.

Exactly three complete screen references are provided:

1. `SCREEN_LOGIN` — `login_material_3/screen.png` — **Welcome Back / Sign In**.
2. `SCREEN_SIGNUP` — `sign_up_material_3/screen.png` — **Create Account / Sign Up**.
3. `SCREEN_PROFILE` — `user_profile_material_3/screen.png` — **User Profile / Account Hub**.

Implement those three screens. Do not add unsupported full screens such as:

- Home dashboard;
- Discover feed;
- Messages inbox;
- password-reset wizard;
- email verification;
- onboarding carousel;
- subscription or Pro upgrade checkout;
- payment method editor;
- detailed account settings;
- standalone profile editor;
- notification center;
- cloud-drive/file manager;
- analytics dashboard;
- contacts/phone-number management;
- biometric-enrollment screen.

Some of these destinations appear as **labels or action icons** in the supplied designs. Their presence does **not** mean their destination screens have been designed or approved.

Supported end-to-end demonstration flow:

```text
Login <----> Sign Up
  |
  | explicit successful DEMO sign-in
  v
Profile
  |
  | demo log out
  v
Login
```

If the project already contains a real, approved auth/navigation flow, integrate with that existing flow instead of overwriting it with demo behavior.

---

## 3. Stitch References Are Not Production Code

The three `code.html` files are web-based design prototypes. **Do not implement Nova by using:**

- `WebView` for these screens;
- HTML, CSS or Tailwind as Android UI;
- JavaScript state or DOM event handlers;
- `setTimeout()` imitation of network authentication;
- web fonts fetched at runtime;
- Material Symbols font files downloaded from Google at runtime;
- source screenshot images as giant noninteractive screen backgrounds;
- original `lh3.googleusercontent.com/aida-public/...` URLs as production image sources.

Translate the intended appearance and behavior into native Compose components, typed state and Kotlin events.

The HTML explicitly demonstrates these interactions:

- Login: password visibility toggle; temporary submit loading text; biometric icon micro-feedback.
- Sign Up: password visibility toggle; password-strength dots changing with input length.
- Profile: two independent switch toggles changing checked/unchecked appearance.

**Do not mistake** those prototypes for proof of real sign-in, registration, Google/Apple federation, biometric validation, data persistence, cloud operations, or security-settings changes.

The HTML contains several buttons/links with no substantive click handling. Implement honest local UX or route only to an actually supported destination; never silently claim a remote operation succeeded.

---

## 4. Official Nova Material 3 Color Scheme

The root `design.md` defines the **light theme** below. Reproduce these values centrally in `ui/theme/Color.kt` and `ColorScheme` rather than scattering literal colors through screen composables.

| Token | Official hex | Purpose |
| --- | --- | --- |
| `primary` | `#2514A5` | Main CTAs, active controls, core branding |
| `onPrimary` | `#FFFFFF` | Icons/text on primary |
| `primaryContainer` | `#E0E0FF` | Light selected pills and accent containers |
| `onPrimaryContainer` | `#08006C` | Text/icons on primary container |
| `secondary` | `#4F46E5` | Supporting interaction accents |
| `onSecondary` | `#FFFFFF` | Content on secondary |
| `surface` | `#FCF8FF` | Main screen/background `Scaffold` |
| `onSurface` | `#1A1B2E` | Primary text |
| `onSurfaceVariant` | `#5B5D72` | Secondary body text and input hints |
| `surfaceVariant` | `#EDE8F8` | M3-style filled text fields and tonal cards |
| `surfaceContainerLow` | `#F6F2FF` | Social buttons and gentle sub-surfaces |
| `surfaceContainer` | `#F1ECFB` | Grouping surfaces and bottom navigation |
| `surfaceContainerHigh` | `#E8E2F5` | Higher-elevation tonal cards/modals |
| `outline` | `#D6D2E6` | Borders and strong separators |
| `outlineVariant` | `#EAE6F5` | Low-emphasis dividers |
| `error` | `#BA1A1A` | Input errors and true destructive semantics |
| `errorContainer` | `#FFDAD6` | Log Out / destructive button surface |
| `onErrorContainer` | `#410002` | Log Out / destructive container content |

Implementation rules:

- Prefer `MaterialTheme.colorScheme.*` at usage sites.
- When `surfaceContainerLow` or another role needs compatibility handling for the repository's current Material 3 version, centralize the alias in the theme layer.
- Main Sign In and Create Account buttons: **`primary` + `onPrimary`**.
- Filled form fields: **`surfaceVariant`** (`#EDE8F8`).
- App canvas: **`surface`** (`#FCF8FF`).
- Log Out button: **`errorContainer` + `onErrorContainer`**.
- Selected bottom-navigation pill: **`primary`** with a white selected icon, following the root design spec.
- Do not use the HTML's `#2A14B4` primary or `#4338CA` primary-container in place of the official root values.
- Do not import colors from the previous project.
- The screenshots show a few minute decorative accents (such as small status/BETA dots); if these cannot be represented with official semantic tokens, define a **documented, centralized decorative token** instead of sprinkling arbitrary hex values or abusing `error` for a non-error state.

No official dark palette is provided for this task. Do not invent a dark theme merely because Material 3 supports one. Preserve the supplied **light** appearance by default.

---

## 5. Official Typography

Use **Roboto Flex**, when a properly licensed and locally bundled version is actually present. Otherwise use a consistent system sans-serif / `FontFamily.Default` fallback; **do not fetch Google Fonts at runtime**.

The root `design.md` defines:

| M3 role | Font size | Line height | Weight |
| --- | ---: | ---: | --- |
| `displaySmall` | 32sp | 38sp | 700 / Bold |
| `headlineMedium` | 24sp | 30sp | 600 / SemiBold |
| `titleMedium` | 18sp | 24sp | 600 / SemiBold |
| `titleSmall` | 15sp | 20sp | 500 / Medium |
| `bodyLarge` | 15sp | 22sp | 400 / Regular |
| `bodyMedium` | 13sp | 18sp | 400 / Regular |
| `labelLarge` | 14sp | 20sp | 600 / SemiBold |
| `labelSmall` | 11sp | 15sp | 500 / Medium |

Build the official `Typography` object once in `ui/theme/Type.kt`.

Role usage:

- `Welcome Back` and `Create Account`: display/headline treatment matching each screenshot.
- User name `Elena Vance`: profile hero headline.
- `Account & Security` / `App Preferences`: section titles.
- Form field user text: body large.
- Supporting copy, settings subtitles and field hints: body medium.
- CTAs and selected navigation labels: label large where visually appropriate.
- Footer/version text: label small.

Respect hierarchy, line-height and `TextOverflow.Ellipsis`. Do not shrink text to tiny sizes to force it into a 390px design.

---

## 6. Spacing, Shape and Component Geometry

Official spacing tokens:

| Name | Value | Primary use |
| --- | ---: | --- |
| `xxs` | 4dp | Micro icon/pill spacing |
| `xs` | 8dp | Tight inline gaps |
| `sm` | 12dp | Field interior and social spacing |
| `md` | 16dp | Horizontal margins, card padding |
| `lg` | 20dp | Form field vertical separation |
| `xl` | 24dp | Section spacing, app bar margins |
| `xxl` | 32dp | Major header/content separation |

Official general shapes:

- Small: **8dp**.
- Medium: **16dp**.
- Large: **24dp**.
- Full: **100dp / `CircleShape`**.

Important component-specific overrides from the same root `design.md`:

- Form text fields: **20dp** corner radius, **56dp** height.
- Primary authentication buttons: full pill, **56dp** height.
- Social auth buttons: full pill, **52dp** recommended height.
- Main profile statistics surface: **24dp** corner radius.
- Settings leading icon container: circular, approximately **40dp** visual box, but touch target should remain accessible.
- Bottom-navigation selected indicator: capsule/pill.

Centralize tokens in a `NovaSpacing` / `NovaShapes` object or equivalent. Use consistent dimensions, but where the **screen image** demonstrably differs on a particular element, preserve the screenshot's visual proportions without changing global tokens for unrelated components.

Avoid generic sharp rectangular cards, excessive shadows, random elevation, or excessive borders.

---

## 7. Theme, Material You and Dynamic Color

Set the **official Nova fixed-indigo light theme** as the baseline for development, previews and screenshot comparisons.

Use native Material 3 theme setup:

- `MaterialTheme(colorScheme = NovaLightColorScheme, typography = NovaTypography, shapes = NovaShapes) { ... }`.
- `Scaffold(containerColor = MaterialTheme.colorScheme.surface)`.
- Material 3 `Button`, `TextField`, `Switch`, `Checkbox`, `NavigationBar`, `ListItem`, `Surface`, and app bars as appropriate.

**Profile screenshot nuance:** The `Material You Theme` preference appears in the **ON** position, while the supplied reference uses a fixed branded indigo palette. Do **not** automatically call `dynamicLightColorScheme()` based solely on this mock state; that would change colors with the device wallpaper and make the implementation diverge from the official reference.

Acceptable base implementation:

- show a local, initially selected demo preference;
- toggle and preserve its UI state;
- continue rendering the fixed Nova palette until a real dynamic-theme requirement is approved.

If real wallpaper-based dynamic theming is later requested, implement it deliberately with clear user-controlled behavior and separate visual regression baselines. Do not pretend a visual preference toggle has changed the device's actual theme if it has not.

---

## 8. Asset Registry and Asset Provenance

The root design defines these visual asset **roles**, but the supplied ZIP has **no standalone asset files** for them:

| Identifier | Role | Reference behavior |
| --- | --- | --- |
| `ASSET_LOGO_NOVA` | Circular stylized infinity-twist / Nova emblem | Login hero, compact sign-up brand mark, small top-bar mark |
| `ASSET_AVATAR_USER` | Professional user-profile portrait | Circular profile hero and small top-app-bar avatar |
| `ASSET_ICONS_SOCIAL` | Google and Apple provider vector marks | Login and sign-up provider buttons |

Rules:

1. First use project-owned, properly supplied image/vector assets if provided later.
2. The ZIP's `screen.png` files are **visual references**, not a replacement for individually managed production image assets.
3. The HTML references `lh3.googleusercontent.com/aida-public/...` remote images. Do not copy these generated prototype URLs into the Android app.
4. If an exact Nova logo vector is not provided, recreate an appropriately recognizable **native vector/Canvas approximation** of the reference or use a documented local fallback. Do not borrow a different company's logo.
5. The login's emblem is more elaborate than the compact sign-up infinity badge. Support the appropriate **visual variant** instead of forcibly stretching one raster image across all roles.
6. For the profile image, prefer a properly supplied/cleared local portrait. Otherwise use a **deterministic, tasteful local avatar placeholder** matching the size/crop/visual density; never use a random person's image as if they were the demo user.
7. Social logos can be represented by locally bundled vector drawables (the supplied HTML contains simple SVG shape references), subject to provider branding requirements. Translate vector paths into Android vector assets; do not embed the HTML rendering layer.
8. Avoid runtime Internet dependence just to show the Nova UI.
9. Preserve attribution/license information for third-party image/vector assets when applicable.
10. If pixel-exact branding or avatar identity is required and source assets are missing, report that limitation; do not claim an exact match.

For meaningful images provide content descriptions; decorative flourishes should generally have `contentDescription = null`.

---

## 9. Native Iconography

Use matching Compose Material icons, `Icons.AutoMirrored` for navigation arrows when appropriate, or local Android VectorDrawable resources.

Important icon roles:

- back/forward;
- mail;
- person;
- lock;
- show/hide password;
- check/check circle;
- Google / Apple provider marks;
- fingerprint;
- edit;
- settings;
- share;
- verification / member star;
- personal information;
- security / shield;
- credit card;
- palette;
- notifications;
- cloud storage;
- logout;
- home, explore/discover, chat/messages and profile.

Match icon semantics, visual weight and alignment. Do not add a large dependency merely to replace one small unavailable icon.

**Do not** use the web Material Symbols font at runtime.

---

## 10. Shared Component Inventory

Prefer reusing small native components across these three screens where it does not distort the screenshot:

- `NovaBrandMark` (hero/compact variants);
- `NovaFormField`;
- `NovaPasswordField`;
- `NovaPrimaryButton`;
- `NovaSocialAuthButton`;
- `NovaDividerLabel`;
- `NovaCheckboxRow`;
- `NovaAvatar`;
- `NovaVerifiedBadge` / `NovaProBadge`;
- `NovaStatsCard`;
- `NovaSettingsRow`;
- `NovaPreferenceSwitchRow`;
- `NovaStorageUsageRow`;
- `NovaBottomNavigation`;
- `NovaLoadingButtonContent`;
- `NovaSectionHeader`.

Shared components should receive plain state and callbacks, not instantiate their own ViewModels. Extract components when truly repeated; do not create a large abstract UI framework for three screens.

---

## 11. Login Screen — Required Visual Structure

Source: `login_material_3/screen.png`.

**Appearance and order:**

1. Light Nova canvas and generous top breathing room.
2. Large, centered circular Nova brand emblem with gentle depth.
3. `Nova` wordmark with tiny decorative accent.
4. Large **`Welcome Back`** headline.
5. Subtitle: **`Sign in to continue exploring your personalized workspace`**.
6. Email label and tinted rounded email field with mail icon; reference hint `name@example.com`.
7. Password label and tinted rounded password field with lock and visibility icons; reference hint `Enter your password`.
8. Inline `Remember me` checkbox row and trailing **`Forgot password?`** action.
9. Prominent full-width indigo pill **`Sign In`** CTA with forward arrow.
10. Divider label **`OR CONTINUE WITH`** with thin side separators.
11. Side-by-side Google and Apple tonal pill buttons.
12. Wide tonal **`Sign in with Biometrics`** button with fingerprint icon.
13. Footer prompt **`Don't have an account? Sign up`**.

Do not add a bottom navigation bar to Login.

Layout guidance:

- Keep the auth form and action hierarchy vertically balanced.
- Form controls are full-width inside comfortable horizontal padding.
- The screenshot represents a compact phone at **390px** width; this is a visual reference, **not** a mandate to hardcode pixel positions.
- Allow vertical scrolling and IME-safe layout on shorter devices so social buttons and the footer remain reachable.
- Do not have the CTA or footer disappear beneath system gesture/navigation bars.

---

## 12. Login — Functional Rules

State to model explicitly:

- `email`;
- `password`;
- `isPasswordVisible`;
- `rememberMe` (reference default: **unchecked**);
- `emailError` / `passwordError` or derived form validation;
- `isSubmitting`;
- one-shot feedback or navigation event.

Expected interactions:

- Email input accepts normal email text; use `KeyboardType.Email` and a meaningful autofill hint when supported.
- Password input uses `PasswordVisualTransformation` unless the visibility action is selected.
- Password visibility icon must update both visual transformation and accessibility label.
- `Remember me` checks/unchecks immediately; a local preference may be saved without storing a password.
- `Sign In` validates required input and a reasonable email format before offering a successful **demo** session.
- Display field-level validation messages without moving unrelated controls unpredictably.
- While processing a simulated demo submit, show a native loading indicator or `Signing In...` state and disable duplicate submission.
- A demo completion may navigate to the supplied `SCREEN_PROFILE`; it must not be presented as remote identity verification.
- `Sign up` navigates to `SCREEN_SIGNUP`.
- `Forgot password?` has no supplied destination, backend or legal copy. Provide clearly scoped demo feedback or integrate an approved existing flow; do not invent reset screens or claim that an email was sent.
- Google/Apple buttons use the provider integration policy in Section 27.
- Biometrics button uses the biometric policy in Section 28.

**Never store plaintext passwords**, log password field contents or hardcode fake production auth secrets.

---

## 13. Sign-Up Screen — Required Visual Structure

Source: `sign_up_material_3/screen.png`.

The composition contains:

1. Upper-left circular tonal **Back** action.
2. Centered compact Nova infinity logo with a tiny decorative badge.
3. Small **`NOVA`** brand label.
4. Prominent **`Create Account`** headline.
5. Subtitle: **`Sign up to start your journey with Nova`**.
6. Soft white rounded registration **form card**.
7. `Full Name` field with person icon; reference hint `Alex Morgan`.
8. `Email address` field with mail icon; reference hint `alex.morgan@domain.com`.
9. `Password` field with lock icon and visibility control.
10. Four-dot password-strength indicator, `Medium security` reference label and `At least 8 chars` helper.
11. `Confirm Password` field with validation/check visual state.
12. Consent row linking **`Terms of Service`** and **`Privacy Policy`**.
13. Full-width **`Create Account`** indigo pill CTA.
14. Divider **`OR REGISTER WITH`**.
15. Side-by-side Google and Apple provider buttons.
16. Footer **`Already have an account? Sign In`**.

No bottom navigation is visible on Sign Up; do not add it.

The screenshot is **390 × 991px** and captures a taller layout; do not set the device viewport to 991px or crop the form/footer. The screen must scroll gracefully on typical phones with the soft keyboard open.

---

## 14. Sign-Up — Input State and Validation

Use a typed, immutable screen state containing at least:

- `fullName`;
- `email`;
- `password`;
- `confirmPassword`;
- `isPasswordVisible`;
- `acceptedTerms`;
- `passwordStrength`;
- `emailError`, `nameError`, `passwordError`, `confirmPasswordError`;
- `isSubmitting`.

Validation rules for the **UI demo**:

- Full Name: require meaningful nonblank input.
- Email: require nonblank, valid email-shaped input; do not claim the address is verified.
- Password: at least **8 characters**, per visible screenshot helper.
- Confirm Password: only show a success/check indicator when **both nonempty actual field values match**.
- Consent: the user must explicitly accept the linked terms before successful registration submission.
- Create Account should provide specific, actionable form errors when invalid.
- The password-strength indicator responds to actual password changes; do not leave a static success-looking strength label after the user edits a weak password.
- The reference HTML modifies strength dots after length reaches **8** and **12** characters. A local four-level demo scoring model is acceptable, but label it as indicative feedback, not verified account security.
- Do not transmit, log or persist raw passwords in the demonstration.

**Important screenshot/HTML discrepancy:** The visible sign-up sample text (`Alex Morgan`, email and password bullets) is mostly defined as HTML `placeholder` values, not confirmed field `value` data. Also, the visual preview contains a checked consent box and a checkmark beside confirmation even though the HTML does not establish entered matching passwords.

Handle this rigorously:

- In **visual previews/tests**, use an explicit sample `SignUpPreviewState` to match the screenshot's filled/selected appearance where appropriate.
- In the **actual interactive sign-up form**, treat placeholders as hints, derive validation only from actual user input and do not default real terms consent to accepted.
- Never present the screenshot's checkmark as proof of password verification while the actual inputs are blank.

This preserves fidelity **without** implementing misleading form behavior.

---

## 15. Sign-Up — Terms, Privacy and Registration Action

`Terms of Service` and `Privacy Policy` are underlined interactive text spans in the source.

- Preserve their independent tap targets and accessible link semantics.
- The HTML `#terms` and `#privacy` fragments do **not** constitute supplied legal documents.
- If real policy URLs or destinations exist in the actual project, use those.
- Otherwise show clearly identified demo/unavailable feedback; **do not fabricate legal text**.
- A real registration flow must not assume consent solely because the screenshot shows a checked checkbox.
- Do not invent marketing consent, data-sharing permissions, age confirmation or extra legal agreements.
- Do not show `Account created successfully` unless a defined local **demo** result or real backend result supports it; distinguish simulated success from a persistent account.
- After successful local demo registration, use an intentional supported navigation target (e.g. supplied Profile or return to Login) and document the chosen behavior consistently.
- Sign Up back and footer Sign In should return to Login using the navigation stack.

---

## 16. Profile Screen — Overall Structure

Source: `user_profile_material_3/screen.png`.

Preserve the following order and relative visual density:

1. Transparent/light top app bar.
2. Left Back control and small Nova brand mark.
3. Centered **`Profile`** title.
4. Trailing Edit icon, Settings icon, and small circular profile avatar.
5. Centered large user portrait in a circular crop with reference status/verification embellishments.
6. Large profile name **`Elena Vance`**.
7. Inline handle **`@elenavance`** and role **`Senior Product Designer`**.
8. Small **`Pro Member`** pill/badge.
9. Wide rounded statistics capsule with three columns.
10. `Edit Profile` primary-like pill CTA and adjacent round Share action.
11. **`Account & Security`** settings group.
12. **`App Preferences`** settings group.
13. Full-width destructive-style **`Log Out`** pill.
14. Centered small version/footer label.
15. Fixed **four-destination bottom navigation** with Profile selected.

The screenshot is **390 × 1208px**. It is a long visual reference, not an exact viewport-height prescription. Let profile content scroll **under a correctly reserved bottom navigation area**, without losing settings rows or the Log Out button.

---

## 17. Profile — Hero and Identity

Seed the profile preview with the **reference presentation data**:

| Field | Reference display |
| --- | --- |
| Display name | `Elena Vance` |
| Handle | `@elenavance` |
| Role | `Senior Product Designer` |
| Membership badge | `Pro Member` |
| Projects | `42` |
| Followers | `12.8k` |
| Rating | `4.9` |

These are **static presentation values from the supplied screenshot**, not verified real user records. They should come from a single deterministic demo profile object, not be repeated as hardcoded strings inside unrelated composables.

Visual details:

- Profile hero is centered; use strong whitespace rather than cards enclosing everything.
- The avatar is circular with subtle border/tonal framing.
- Small overlaid dots/badges should reproduce the screenshot's proportions without incorrectly implying actual live presence or verified identity.
- Use the correct member icon treatment and indigo/lavender tones.
- The handle is emphasized relative to the role, which is supporting copy.
- Top bar avatar and hero avatar should represent the **same** local profile asset or fallback.

If actual authentication is integrated later, replace only the data source; do not build a fake live identity system.

---

## 18. Profile — Statistics Card

The statistics capsule contains three columns, separated by subtle vertical dividers:

1. `42` — `PROJECTS`;
2. `12.8k` — `FOLLOWERS`;
3. `4.9` with small accent/star — `RATING`.

Build one rounded `Surface` using the official Nova tonal background and approximately **24dp** corner radius.

- Each metric stays horizontally centered.
- Vertical dividers use a low-emphasis outline token.
- Labels use small readable typography rather than tiny raster-like text.
- Do not call a server to fetch these values.
- Do not randomly change statistics between recompositions or app launches.
- Do not imply these statistics are validated platform analytics.

---

## 19. Profile — Edit, Share and Top-Bar Actions

Visible interactive affordances:

- top-bar Back;
- top-bar Edit;
- top-bar Settings;
- `Edit Profile` CTA;
- Share profile button;
- avatar/status ornaments if decorative.

Rules:

- Back uses `NavController.popBackStack()` or existing native navigation.
- Edit Profile top-bar action and wide CTA should dispatch the **same semantic intent**.
- No full edit screen is supplied. Do not invent a multi-field profile editor without an explicit request.
- Settings icon must not generate a new settings destination by assumption.
- A small native Snackbar or another explicit no-destination demo response is acceptable for unsupported actions.
- Share may use a native `Intent.ACTION_SEND` with **non-sensitive public demo profile text only** if this interaction is implemented.
- Do not share passwords, internal user IDs, device data, security settings or fictional public profile URLs as real links.
- Profile header icons require accessible labels and touch targets.

---

## 20. Profile — Account & Security Section

The first settings group has these exact visible rows:

**Personal Information**

- leading account/person icon in a circular tonal box;
- title `Personal Information`;
- supporting subtitle `Name, email, contact details`;
- trailing chevron.

**Security & Biometrics**

- leading shield/security icon;
- title `Security & Biometrics`;
- supporting text `Two-factor authentication enabled` in the reference state;
- trailing **on-state switch** rather than a chevron.

**Payment Methods**

- leading credit-card icon;
- title `Payment Methods`;
- supporting text `Visa ending in 4289`;
- trailing chevron.

The rows should use Material 3 tonal group styling, sufficient vertical hit areas and consistent icon alignment.

Interaction rules:

- The Security & Biometrics switch is explicitly interactive in HTML and must toggle **local UI state**.
- The default screenshot has it ON; preview fixtures should preserve that state.
- **Do not** claim that toggling it enables/disables a real 2FA factor or enrolls a biometric device without an approved auth backend.
- Ensure the subtitle reflects the demo-state selection in a nonmisleading way, or clearly treat it as static reference copy in snapshot tests.
- `Payment Methods` is a demonstration row only; do not request or store payment-card details, send charges or build a payment processor.
- Personal Information and Payment Methods do not have supplied destination screens. Use explicit local feedback or an existing approved destination; no invented forms.

---

## 21. Profile — App Preferences Section

The second settings group shows:

**Material You Theme**

- palette icon;
- title `Material You Theme`;
- subtitle `Dynamic color palette adaptation`;
- trailing ON switch.

**Push Notifications**

- bell/notification icon;
- title `Push Notifications`;
- subtitle `Activity, messages, and mentions`;
- trailing chevron.

**Data & Cloud Storage**

- cloud icon;
- title `Data & Cloud Storage`;
- subtitle `18.4 GB of 50 GB used`;
- displayed progress `37%`;
- compact indigo progress bar on a low-contrast track.

Rules:

- Material You switch uses the theme-demo policy in Section 7.
- The two switches on Profile are **independent**, not bound to the same boolean.
- Push Notifications is a navigable-looking row, not evidence of a working push service. Do not automatically request notification permission or create a notification setup screen.
- Storage is seeded UI data, not real cloud usage. Compute its percentage from the same source values rather than hardcoding an inconsistent progress value.
- `18.4 / 50 = 36.8%`, displayed as **37%** rounded.
- Clamp progress between 0 and 1 for `LinearProgressIndicator` and preserve consistent layout when it changes.
- Do not request device-storage permissions, connect a cloud provider, or show upload/delete operations merely because a usage meter exists.

---

## 22. Profile — Log Out and Footer

The screenshot includes a full-width soft-red **`Log Out`** pill below settings.

Required colors from the root design:

- background: `errorContainer` = `#FFDAD6`;
- label/icon: `onErrorContainer` = `#410002`.

Behavior:

- In the local demo, clear the **demo session state** and navigate to Login, removing authenticated Profile from the reachable back stack where practical.
- Never present the demo log out as revoking an actual remote token unless real auth exists and confirms it.
- Do not wipe unrelated data or account settings during a visual demo unless persistence behavior is explicitly specified.
- If a confirmation dialog is desired later, request or apply an approved UX change; the screenshot does not define one.

The footer reference text is:

`Nova App v2.4.0 (Build 382) • Material 3`

Treat this as **supplied display/reference copy**, not verified build metadata. If actual application version/build numbers exist, prefer an intentional mapping rather than falsely asserting the screenshot's version is the currently installed release.

---

## 23. Profile — Bottom Navigation

The only supplied screen showing a bottom navigation bar is **Profile**.

Display four destinations in this order:

1. `Home`;
2. `Discover`;
3. `Messages`;
4. `Profile`.

Profile is selected in the reference.

Use `NavigationBar` / `NavigationBarItem` from `androidx.compose.material3` and the root `design.md` tonal specifications:

- tonal bar background `surfaceContainer` = `#F1ECFB`;
- **selected indicator** `primary` = `#2514A5`;
- selected icon in white;
- selected label emphasized;
- inactive items muted with `onSurfaceVariant`.

The bottom bar:

- respects bottom system/gesture insets;
- does not overlap the scrollable profile content;
- uses stable item sizes and 48dp-or-better touch targets;
- is not added to Login or Sign Up by default.

Only `Profile` has a supplied destination screen among these four items. Keep `Home`, `Discover`, and `Messages` visually correct, but do not build imaginary destination screens. If an actual project contains those screens later, integrate them via its real navigation graph. Otherwise provide honest demo feedback without fake route transitions.

---

## 24. Mock Profile and UI Data

Use deterministic seeded presentation state; do not scatter values through composables.

Suggested data types:

```kotlin
data class DemoProfile(
    val id: String,
    val displayName: String,
    val handle: String,
    val role: String,
    val isProMember: Boolean,
    val projectsCount: Int,
    val followersLabel: String,
    val rating: Double,
    val paymentCardLastFour: String?,
    val storageUsedGb: Double,
    val storageCapacityGb: Double,
    val avatarResource: Int?,
)
```

Example seeded state corresponding to the supplied screenshot:

```kotlin
val novaPreviewProfile = DemoProfile(
    id = "nova-demo-profile",
    displayName = "Elena Vance",
    handle = "@elenavance",
    role = "Senior Product Designer",
    isProMember = true,
    projectsCount = 42,
    followersLabel = "12.8k",
    rating = 4.9,
    paymentCardLastFour = "4289",
    storageUsedGb = 18.4,
    storageCapacityGb = 50.0,
    avatarResource = null, // Resolve to supplied local asset or deterministic fallback.
)
```

These values come from the design screenshot and are **not real external user records**.

Keep separate data categories:

- immutable preview/profile content;
- editable login/sign-up inputs;
- local toggles/preferences;
- local demo session state;
- transient loading/error/navigation events.

**Do not** use `Alex Morgan` from the Sign Up placeholders as if it were automatically the same person as screenshot user `Elena Vance`. They are two different reference contexts. A later real integrated user identity model should explicitly own these transitions.

---

## 25. Native Android Architecture

Use a clean MVVM route/render boundary:

```kotlin
@Composable
fun LoginRoute(
    viewModel: LoginViewModel,
    onNavigateToSignUp: () -> Unit,
    onDemoSignedIn: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LoginScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
    )
    // Collect one-shot navigation effects using lifecycle-aware orchestration.
}

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onAction: (LoginAction) -> Unit,
) {
    // Stateless visual renderer.
}
```

Apply equivalent separation to:

- `SignUpRoute` / `SignUpScreen`;
- `ProfileRoute` / `ProfileScreen`.

Architecture requirements:

- A ViewModel owns and reduces user actions into immutable state.
- Compose renders state and emits typed actions.
- Use `StateFlow<ScreenUiState>`.
- Collect UI state with `collectAsStateWithLifecycle()`.
- Avoid state mutations inside deeply nested shared components.
- Small purely visual concerns (`remember` for transient animation focus, etc.) are acceptable when they do not duplicate business/UI source-of-truth state.
- Use `SavedStateHandle` when needed for meaningful form/navigation restoration.
- Keep business rules and validation pure/testable outside composables.
- Avoid an elaborate clean-architecture framework for only three UI-demo screens.
- Preserve the existing package name, Gradle structure and navigation conventions if a real repository is supplied later.

---

## 26. Explicit Screen UI State and Actions

Model events explicitly, rather than binding unrelated booleans together.

Useful shape examples:

```kotlin
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isSubmitting: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
)

sealed interface LoginAction {
    data class EmailChanged(val value: String) : LoginAction
    data class PasswordChanged(val value: String) : LoginAction
    data object TogglePasswordVisibility : LoginAction
    data object ToggleRememberMe : LoginAction
    data object Submit : LoginAction
    data object ForgotPassword : LoginAction
    data object SignUp : LoginAction
    data class SocialProviderClicked(val provider: SocialProvider) : LoginAction
    data object BiometricClicked : LoginAction
}

enum class SocialProvider { Google, Apple }
```

For Sign Up, represent:

- field changed actions;
- password visibility;
- consent toggle;
- terms/privacy link activation;
- submit action;
- navigation to Login;
- social-provider selection.

For Profile, represent:

- `EditProfileClicked`;
- `SettingsClicked`;
- `ShareProfileClicked`;
- `SecurityPreferenceChanged(Boolean)`;
- `MaterialYouPreferenceChanged(Boolean)`;
- `PersonalInformationClicked`;
- `PaymentMethodsClicked`;
- `PushNotificationsClicked`;
- `StorageClicked` if truly interactive;
- `BottomDestinationClicked(Destination)`;
- `LogOutClicked`.

Use one-shot events/effects for snackbars and navigation rather than encoding a permanently consumed navigation command into persistent UI state.

---

## 27. Google and Apple Social Authentication

Both Login and Sign Up display **Google** and **Apple** buttons with brand marks.

The current materials show **only their UI**, not a provider SDK, identity client ID, OAuth callback, auth server or Apple Sign In integration.

For the base UI demo:

- render both as native touchable M3 tonal buttons;
- expose separate provider-click actions;
- display a clearly identified `Demo only — Google sign-in is not connected` or equivalent feedback when no real integration exists;
- do not show a fake Google/Apple consent screen;
- do not pretend a provider session was created;
- do not request arbitrary account, contacts or other permissions;
- do not copy secret OAuth IDs/keys from the internet.

If approved real social sign-in is later required, integrate the correct supported Android provider flows, scopes, redirect configuration and backend token verification. Do not implement it speculatively as part of UI reconstruction.

---

## 28. Biometric Sign-In and Security Boundaries

The Login screenshot includes a full-width **`Sign in with Biometrics`** action and a fingerprint icon. The HTML only changes its icon briefly; it does **not** perform biometric authentication.

Base UI requirements:

- reproduce the tonal pill and fingerprint icon;
- expose a `BiometricClicked` action;
- provide honest demo micro-feedback without calling an actual secure sign-in successful;
- preserve the screen's accessibility and loading semantics.

Only integrate actual AndroidX BiometricPrompt when a real requirement and credential/session handling exist. Then:

- inspect device biometric capability;
- follow Android prompt lifecycle and platform APIs;
- handle no enrollment, unavailable hardware, cancellation, success and error;
- never treat biometric success **by itself** as proof of server identity or create undocumented account credentials;
- add only permissions/dependencies demonstrably necessary for that approved feature.

Likewise, Profile's `Security & Biometrics` switch is a local UI preference until real security APIs are provided.

---

## 29. Security and Privacy in This UI Demo

Keep the mock implementation safe even though it is not a backend product.

- Never persist raw passwords with `DataStore`, Room, SharedPreferences or files.
- Do not log form values containing passwords, tokens or payment data.
- Do not add hardcoded secret keys.
- Do not ask for camera, contacts, phone, location, broad storage or SMS access.
- Do not contact a network service simply because the UI contains a Login button.
- Do not fabricate actual 2FA enrollment, payment status, storage usage, followers, profile verification or a Pro subscription.
- The card label `Visa ending in 4289` contains only design/demo last-four digits. Do not add a card-number field or backend.
- Do not persist mock consent as valid legal consent to unprovided terms.
- Keep error messages user-friendly and avoid disclosing internal state.
- Use encrypted/token-safe persistence only if a real authenticated product scope and threat model are later defined.

---

## 30. Navigation and Back-Stack Semantics

Use Compose Navigation or an existing clean native navigation solution.

Suggested conceptual destinations:

```text
login
sign_up
profile
```

Rules:

- `Login -> Sign Up` via the footer.
- `Sign Up -> Login` via Back or footer Sign In.
- Valid local **demo** Sign In may transition to Profile.
- Profile Back should use the actual navigation stack where back is meaningful; define a safe Login fallback when entered directly in a preview/demo environment.
- Demo Log Out must return to Login and avoid Back immediately reopening Profile.
- The Profile's bottom tabs not represented by supplied screens must not silently navigate to invented layouts.
- Do not add deep links, account routers or nested nav graphs without a reason.
- Apply `popUpTo` / `launchSingleTop` thoughtfully to prevent duplicate authentication routes.

---

## 31. Deterministic Local State and Persistence

The baseline app must be fully demonstrable **offline**.

Prefer:

- in-memory `DemoSessionRepository`;
- immutable seeded `DemoProfileRepository` or a simple source object;
- ViewModel state for current form edits and screen preferences;
- optional DataStore **only** for nonsecret preferences when persistence is an explicit requirement.

Keep stable across screenshot runs:

- profile name, handle, role, badge;
- project/follower/rating metrics;
- masked card suffix;
- storage usage values;
- initial two Profile toggle states;
- initial Profile tab selection;
- view states explicitly selected for previews.

Do not generate random user identities, follower counts, security states, passwords or profile photos on each launch.

If sign-up demo input is used to alter any displayed profile fields, implement that as one intentional, consistent demo-account workflow instead of accidentally mixing fields from `Alex Morgan` and `Elena Vance`.

---

## 32. Responsive Layout and Scrolling

The references are mobile portrait images, approximately **390px wide**.

Prioritize that composition while supporting normal Android sizes:

- Form screens: `Scaffold` plus scrollable content (`Column.verticalScroll(...)` or a suitable lazy container), centered and constrained appropriately on wide displays.
- Profile: a single vertical content scroller (`LazyColumn` or `Column.verticalScroll`) plus a stable scaffold bottom navigation bar.
- Avoid nested vertical scroll conflicts.
- Use `fillMaxWidth()`, flexible text layout and size constraints instead of pixel positions tied to reference device height.
- Preserve the hero's centered alignment even on narrower phones.
- Avoid clipping form validation errors or hiding the Sign Up footer behind the keyboard.
- Use `imePadding` / scaffold insets appropriately and scroll focused inputs into view when practical.
- Respect font scaling and longer localized strings.
- On tablets/foldables, center or constrain auth content rather than stretching single input fields across the entire screen. Do not invent a new two-pane dashboard.
- Do not assume long screenshots represent the actual visible viewport height.

Use system-safe insets only once at the correct layout boundary to avoid double padding.

---

## 33. Keyboard, Focus and Input UX

- Email fields use `KeyboardType.Email`.
- Password fields use `KeyboardType.Password` with secure visual transformation.
- Full Name uses appropriate capitalization and text-field semantics.
- Set IME actions to `Next` or `Done` according to field order.
- Progress focus logically between fields.
- On submit, hide the keyboard and focus errors as appropriate without surprise navigation.
- Do not enable password autocorrect.
- Preserve entered text through recomposition and reasonable configuration changes.
- Password fields must not expose plaintext in previews/logs beyond an intentionally documented secure test fixture.
- Use 48dp-or-greater tap targets for eye icons, checkboxes and compact icons where practical.

---

## 34. Accessibility and Semantics

Use proper semantics for:

- form field labels and error descriptions;
- show/hide password controls;
- Remember me checkbox;
- Terms and Privacy links;
- Sign In and Create Account loading/disabled states;
- social-provider buttons;
- biometric action;
- Back, Edit, Settings, Share and Log Out;
- Profile hero image;
- Pro badge;
- each statistics label/value pairing;
- security and theme switches;
- storage progress;
- bottom-navigation selected state.

Do not rely only on color to convey:

- checkbox state;
- password quality;
- confirmation match;
- selected tab;
- switch state;
- validation errors.

Prefer Material semantics (`Role.Switch`, `Role.Checkbox`, progress semantics, meaningful `contentDescription`, `stateDescription`) where supported. Keep touch targets at least **48dp** when practical.

Avoid redundant accessibility announcements: decorative dots, shadows and gradients should not be announced individually.

---

## 35. Animation and Micro-Feedback

Use restrained, native Material behavior:

- password eye icon state change;
- checkbox check animation;
- button press feedback/ripple;
- password-strength dot changes;
- password-confirmation state;
- switch thumb/check movement;
- loading feedback in the main auth CTA;
- optionally subtle biometric demo icon feedback;
- Snackbar for genuinely unsupported demo actions.

Do not add excessive bounce, particle effects, confetti, onboarding animations, massive parallax or indefinite fake loading.

Honor reduced-motion/accessibility preferences where relevant.

The HTML's `setTimeout` transitions are **prototype visual demonstrations**; they are not a requirement to simulate exact fake network latency or pretend an external service responded.

---

## 36. Validation, Empty States and Failure Feedback

Although screenshots show polished default/success visuals, handle reasonable interactive failures:

**Login**

- empty email;
- malformed email;
- empty password;
- duplicate taps while submitting.

**Sign Up**

- missing full name;
- missing or malformed email;
- password shorter than 8 characters;
- confirmation mismatch;
- unchecked terms consent;
- duplicate registration taps.

**Profile**

- demo action without supported destination;
- unavailable avatar asset;
- very long display name/handle;
- storage value at zero or beyond reported capacity (clamp progress safely);
- local toggle state changes.

For unsupported network/provider/security operations, tell the truth about demo availability rather than displaying a fake success state.

Preserve geometry when a local avatar/vector fallback is used.

---

## 37. Project Structure (Suggested, Not Mandatory)

If starting from an empty Android project, a modest feature-oriented structure may look like:

```text
app/src/main/java/<existing-or-chosen-package>/
    MainActivity.kt
    ui/
        theme/
            Color.kt
            Type.kt
            Shape.kt
            Theme.kt
        components/
            NovaBrandMark.kt
            NovaFormField.kt
            NovaButtons.kt
            NovaAvatar.kt
            NovaSettingsRow.kt
            NovaBottomNavigation.kt
        login/
            LoginRoute.kt
            LoginScreen.kt
            LoginUiState.kt
            LoginViewModel.kt
        signup/
            SignUpRoute.kt
            SignUpScreen.kt
            SignUpUiState.kt
            SignUpViewModel.kt
        profile/
            ProfileRoute.kt
            ProfileScreen.kt
            ProfileUiState.kt
            ProfileViewModel.kt
    navigation/
        NovaNavHost.kt
        NovaDestination.kt
    data/
        DemoProfile.kt
        DemoSessionRepository.kt
        DemoProfileRepository.kt
    validation/
        AuthFormValidation.kt

app/src/main/res/
    drawable/
    font/               # only if a real licensable bundled font is provided
    values/strings.xml
```

Use this only as a **template**. If a real repository exists, preserve its package name, Gradle setup, clean conventions and existing files.

Do not refactor the whole app because the suggested directories differ.

---

## 38. Dependency and Platform Rules

Before changing Gradle:

1. Inspect existing AGP, Kotlin, Compose BOM and dependency versions.
2. Reuse existing Compose Material 3 and Navigation dependencies where available.
3. Add `lifecycle-runtime-compose` only where needed for lifecycle-aware collection.
4. Prefer native `androidx.compose.material3` components over legacy `androidx.compose.material`.
5. Do not add Coil or another network image loader **by default**: the Nova ZIP does not provide a remote-media requirement, and local/vector placeholders suffice for the baseline.
6. Do not add Retrofit/Ktor, Firebase Auth, Room, ExoPlayer, payment SDKs or cloud SDKs speculatively.
7. Do not add AndroidX Biometric unless actual authentication is approved.
8. Do not add Google Sign-In / Credential Manager provider configuration unless actual provider integration is approved.
9. Do not add manifest permissions without a feature-level requirement.
10. Keep the project buildable on the repository's supported SDK/minSdk instead of inventing a version policy from screenshots.

**No live backend or Internet permission is required for the standalone Nova UI demo.**

---

## 39. Compose Preview Fixtures

Create deterministic previews for key screen states, ideally using the same reusable components:

- Login default, remember-me checked, password visible, invalid input, loading state.
- Sign Up screenshot-like reference state, blank interactive state, password weak/medium/strong, mismatch, consent unchecked, submitting.
- Profile default reference data, security switch off, theme switch off, avatar fallback, long text, compact screen.

Never require Internet access, backend login, provider OAuth or a real user account just to render previews.

Document any preview-only difference from a valid interactive default, especially the Sign Up screenshot's sample placeholders and checked consent.

---

## 40. Functional Unit Tests

Where the Android project has or can reasonably support a small test suite, at minimum test pure state/validation logic:

**Login**

- empty and invalid email validation;
- password required;
- remember-me toggle;
- password visibility toggle;
- submit avoids duplicate transitions;
- local demo sign-in event is emitted only through its explicitly defined path.

**Sign Up**

- required full name;
- email format;
- password minimum length;
- password-strength state changes;
- mismatch vs matching nonempty passwords;
- terms consent required;
- Back / Sign In route targets;
- no false success state from placeholders.

**Profile**

- reference profile data seeded consistently;
- security and theme switches toggle independently;
- storage percentage calculates to 37% for 18.4/50;
- storage progress clamps correctly;
- logout clears the demo session;
- action intents for unsupported destinations do not create phantom routes.

Do not add a large testing framework just to cover three screens.

---

## 41. Visual Comparison and Screenshot QA

Perform screen-by-screen comparisons against the **actual three supplied images**.

Verify:

- common light `surface` background;
- official indigo CTA shade, not Stitch Tailwind primary;
- header position/size;
- appropriate Nova logo variant;
- text hierarchy and line wrapping;
- field height, fill color, icon alignment and rounded corners;
- login checkbox/password and social-buttons spacing;
- sign-up white card and consent presentation;
- sign-up password-strength and confirmation layout;
- profile avatar size and badge alignment;
- profile stats card widths and dividers;
- setting group hierarchy and switch placement;
- 37% storage text and bar;
- destructive logout treatment;
- fixed profile bottom navigation, insets and correct selected pill;
- no content cut off on small screens;
- no imported components, text or colors from the old app.

Do not treat a screen screenshot as a tappable image implementation. Every visible interactive element should be a real Compose control or a correctly labeled deliberate demo affordance.

---

## 42. Performance and Recomposition Discipline

The current Nova app has only three compact screens. Optimize for clarity and smoothness, not premature infrastructure.

- Use immutable state objects.
- Keep derived field validation stable and testable.
- Avoid allocating unnecessarily expensive objects on every keystroke/recomposition.
- Use stable keys only where lists truly need them.
- Do not create image loaders, new repositories or coroutines in leaf composables on each recomposition.
- Do not debounce simple local UI state changes unnecessarily.
- Avoid unnecessary always-running animations or background workers.
- Keep profile screen scrolling smooth and navigation free of duplicate destinations.

---

## 43. Strings and Localization

Source-visible English UI labels should match the reference by default:

- `Nova`;
- `Welcome Back`;
- `Sign In`;
- `Sign up`;
- `Forgot password?`;
- `Remember me`;
- `Sign in with Biometrics`;
- `Create Account`;
- `Full Name`;
- `Email address`;
- `Password`;
- `Confirm Password`;
- `Medium security` (reference-only default display);
- `At least 8 chars`;
- `Terms of Service`;
- `Privacy Policy`;
- `Profile`;
- `Edit Profile`;
- `Pro Member`;
- `Account & Security`;
- `Personal Information`;
- `Security & Biometrics`;
- `Payment Methods`;
- `App Preferences`;
- `Material You Theme`;
- `Push Notifications`;
- `Data & Cloud Storage`;
- `Log Out`;
- `Home`;
- `Discover`;
- `Messages`.

Keep reusable strings in Android `strings.xml` where practical. Keep profile display data in demo data objects, not separate per-screen string resources.

Do not arbitrarily translate the reference into another language or replace the product name. If localization is requested later, preserve content semantics and responsive layout.

---

## 44. Permission, Networking and Background Work Policy

For the baseline UI-only implementation:

- No `INTERNET` permission is needed for local UI, locally seeded profile state or vector art.
- No biometric or OAuth permissions are needed for honest demo buttons.
- No notification runtime permission is needed for a settings row alone.
- No storage permission is needed for a static storage meter.
- No camera, microphone, contacts, location, phone or SMS permissions are justified by these screens.
- No app-service, upload worker, remote push service or backend polling is justified.

If a later requested real feature requires any permission, explain why, scope it narrowly and handle denial gracefully.

---

## 45. Required Workflow for an Implementation Agent

Before writing code:

1. Inspect root `design.md` and **all three** `screen.png` images.
2. Inspect each corresponding `code.html` for UI labels, fields and demo interactions.
3. Inspect any real Android project that has actually been supplied; if none, state that the source repository is not yet available.
4. Inventory genuine local image/vector/font assets; do not assume the ZIP contains standalone photos/logos.
5. Define the fixed Nova theme and reusable components.
6. Define typed, deterministic UI states and navigation for the three screens.
7. Implement Login, Sign Up and Profile in native Compose.
8. Connect supported routes and local demo interactions.
9. Add form validation and honest placeholder feedback.
10. Compare visually with each supplied screenshot and correct material mismatches.
11. Run available build/tests and explicitly report any that could not be run.
12. Summarize implementation scope, remaining unimplemented real-backend features and any missing assets.

Do not claim a build, emulator review, screenshot diff or authentication integration succeeded unless it was actually performed.

---

## 46. Acceptance Checklist — Sources and Scope

- [ ] Only the three Nova screen references define the complete screen scope.
- [ ] The root `design.md` is the official token source.
- [ ] Embedded Stitch theme colors do not override root values.
- [ ] Stitch HTML serves as behavior/copy reference only.
- [ ] No WebView, Tailwind, HTML screen, JavaScript UI state or `aida-public` hotlinks are used.
- [ ] No unsupported Home/Discover/Messages/Settings/Edit/Payment/Reset full screens are invented.
- [ ] Missing native source/assets are reported honestly.
- [ ] Flutter is not implemented without a separate request.

---

## 47. Acceptance Checklist — Visual Fidelity

- [ ] `surface` = `#FCF8FF`.
- [ ] `primary` = `#2514A5`.
- [ ] Form field fill = `#EDE8F8` and field radius is about 20dp.
- [ ] Primary CTAs are full-width/56dp and full pill.
- [ ] Typography hierarchy follows official Nova scale.
- [ ] Login content order and social/biometrics area match its screenshot.
- [ ] Sign Up contains the rounded white form card, four-dot meter and terms row.
- [ ] Profile header, avatar, badge, stats and actions match their reference density.
- [ ] Profile contains the two exact settings groups and their rows.
- [ ] Profile two switches use correct initial visual states and independent state.
- [ ] Storage shows 18.4 of 50GB and approximately 37%.
- [ ] Logout uses `errorContainer` / `onErrorContainer`.
- [ ] Profile bottom navigation contains Home / Discover / Messages / Profile with Profile selected.
- [ ] No bottom navigation is added to Login or Sign Up.
- [ ] Screens remain usable on shorter/taller Android phones and with IME open.
- [ ] Logo/avatar absence has an intentional local fallback without unrelated remote assets.

---

## 48. Acceptance Checklist — Interaction and Integrity

- [ ] Login password visibility and Remember me toggle work.
- [ ] Login validation blocks invalid demo submission.
- [ ] Sign In shows honest demo loading/result behavior, not fake backend verification.
- [ ] Sign Up password visibility and strength indicator react to input.
- [ ] Confirm Password only validates real matching, nonempty values.
- [ ] Interactive Sign Up requires explicit terms consent.
- [ ] Sign Up ↔ Login navigation works.
- [ ] Profile two switches can be toggled independently.
- [ ] Profile nonimplemented destinations receive meaningful demo feedback instead of phantom screens.
- [ ] Share never leaks sensitive data.
- [ ] Profile logout clears the demo session and returns to Login appropriately.
- [ ] Profile seed data is deterministic and rendered from one object.
- [ ] Storage progress is derived, not an unrelated hardcoded percent.
- [ ] No credentials, access tokens or private API keys are embedded or logged.
- [ ] No unjustified SDK dependencies or permissions are introduced.
- [ ] Compose UI state is immutable and collected lifecycle-aware.
- [ ] Tests/previews are offline and deterministic.

---

## 49. Final Implementation Principle

When faced with uncertainty, choose the following order of decisions:

**Supplied Nova screen screenshot → root Nova `design.md` → genuinely supplied existing Android code → matching Stitch interaction reference → modest native Android implementation with deterministic demo data.**

For anything that claims real authentication, identity, provider authorization, profile verification, payment, 2FA, notifications or cloud storage, use the stricter rule:

**Verified existing capability or explicit new requirement → correct platform integration → truthful result state.**

The goal is **not** to build a generic social network, dashboard or authentication SaaS.

The goal is to reproduce **Nova's three supplied Material 3 screens** as a polished, coherent, native **Jetpack Compose** experience: faithful branding and layout, reliable field/toggle/navigation behavior, honest mock functionality, clean architecture and reproducible UI demos.
