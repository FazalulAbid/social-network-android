package com.fifty.socialnetwork.featureprofile.presentation.editprofile

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.presentation.components.StandardTextField
import com.fifty.socialnetwork.core.presentation.components.StandardToolbar
import com.fifty.socialnetwork.core.presentation.ui.theme.ProfilePictureSizeLarge
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceLarge
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceMedium
import com.fifty.socialnetwork.core.presentation.util.CropActivityResultContract
import com.fifty.socialnetwork.core.presentation.util.UiEvent
import com.fifty.socialnetwork.core.presentation.util.asString
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.featureprofile.presentation.util.EditProfileError
import com.google.accompanist.flowlayout.MainAxisAlignment
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalCoilApi::class)
@Composable
fun EditProfileScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: EditProfileViewModel = hiltViewModel(),
    profilePictureSize: Dp = ProfilePictureSizeLarge
) {
    val profileState = viewModel.profileState.value

    val cropProfilePictureLauncher = rememberLauncherForActivityResult(
        contract = CropActivityResultContract(1f, 1f)
    ) {
        viewModel.onEvent(EditProfileEvent.CropProfilePicture(it))
    }
    val cropBannerImageLauncher = rememberLauncherForActivityResult(
        contract = CropActivityResultContract(5f, 2f)
    ) {
        viewModel.onEvent(EditProfileEvent.CropBannerImage(it))
    }
    val profilePictureGalleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        cropProfilePictureLauncher.launch(it)
    }
    val bannerImageGalleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        cropBannerImageLauncher.launch(it)
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
                is UiEvent.Navigate -> {

                }
                is UiEvent.NavigateUp -> {
                    onNavigateUp()
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            onNavigateUp = onNavigateUp,
            showBackArrow = true,
            navActions = {
                IconButton(onClick = {
                    viewModel.onEvent(EditProfileEvent.UpdateProfile)
                }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(R.string.save_changes),
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.edit_your_profile),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            BannerEditSection(
                bannerImage = rememberImagePainter(
                    data = viewModel.bannerUri.value
                        ?: "${Constants.DEBUG_BASE_URL}${profileState.profile?.bannerUrl}",
                    builder = {
                        crossfade(true)
                    }
                ),
                profileImage = rememberImagePainter(
                    data = viewModel.profilePictureUri.value
                        ?: "${Constants.DEBUG_BASE_URL}${profileState.profile?.profilePictureUrl}",
                    builder = {
                        crossfade(true)
                    }
                ),
                profilePictureSize = profilePictureSize,
                onBannerClick = {
                    bannerImageGalleryLauncher.launch("image/*")
                },
                onProfilePictureClick = {
                    profilePictureGalleryLauncher.launch("image/*")
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpaceLarge)
            ) {
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    text = viewModel.usernameState.value.text,
                    onValueChange = {
                        viewModel.onEvent(
                            EditProfileEvent.EnteredUsername(it)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    hint = stringResource(id = R.string.username),
                    error = when (viewModel.usernameState.value.error) {
                        is EditProfileError.FieldEmpty -> {
                            stringResource(id = R.string.this_field_cant_be_empty)
                        }
                        else -> ""
                    },
                    leadingIcon = Icons.Default.Person
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    text = viewModel.githubTextFieldState.value.text,
                    modifier = Modifier
                        .fillMaxWidth(),
                    hint = stringResource(id = R.string.github_profile_url),
                    error = when (viewModel.githubTextFieldState.value.error) {
                        is EditProfileError.FieldEmpty -> {
                            stringResource(id = R.string.this_field_cant_be_empty)
                        }
                        else -> ""
                    }, leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_github_logo),
                    onValueChange = {
                        viewModel.onEvent(
                            EditProfileEvent.EnteredGitHubUrl(it)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    text = viewModel.instagramTextFieldState.value.text,
                    modifier = Modifier
                        .fillMaxWidth(),
                    hint = stringResource(id = R.string.instagram_profile_url),
                    error = when (viewModel.instagramTextFieldState.value.error) {
                        is EditProfileError.FieldEmpty -> {
                            stringResource(id = R.string.this_field_cant_be_empty)
                        }
                        else -> ""
                    }, leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_instagram_logo),
                    onValueChange = {
                        viewModel.onEvent(
                            EditProfileEvent.EnteredInstagramUrl(it)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    text = viewModel.linkedInTextFieldState.value.text,
                    modifier = Modifier
                        .fillMaxWidth(),
                    hint = stringResource(id = R.string.linkedin_profile_url),
                    error = when (viewModel.linkedInTextFieldState.value.error) {
                        is EditProfileError.FieldEmpty -> {
                            stringResource(id = R.string.this_field_cant_be_empty)
                        }
                        else -> ""
                    }, leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_linkedin_logo),
                    onValueChange = {
                        viewModel.onEvent(
                            EditProfileEvent.EnteredLinkedInUrl(it)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    text = viewModel.bioState.value.text,
                    modifier = Modifier
                        .fillMaxWidth(),
                    hint = stringResource(id = R.string.your_bio),
                    error = when (viewModel.bioState.value.error) {
                        is EditProfileError.FieldEmpty -> {
                            stringResource(id = R.string.this_field_cant_be_empty)
                        }
                        else -> ""
                    }, singleLine = false,
                    maxLines = 3,
                    leadingIcon = Icons.Default.Description,
                    onValueChange = {
                        viewModel.onEvent(
                            EditProfileEvent.EnteredBio(it)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(SpaceLarge))
                Text(
                    text = stringResource(R.string.select_your_top_3_skills),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                com.google.accompanist.flowlayout.FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    mainAxisAlignment = MainAxisAlignment.Center,
                    mainAxisSpacing = SpaceMedium,
                    crossAxisSpacing = SpaceMedium
                ) {
                    viewModel.skills.value.skills.forEach { skill ->
                        com.fifty.socialnetwork.featureprofile.presentation.editprofile.components.Chip(
                            text = skill.name,
                            selected = viewModel.skills.value.selectedSkills.any { it.name == skill.name },
                            onChipClick = {
                                viewModel.onEvent(EditProfileEvent.SetSkillSelected(skill))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BannerEditSection(
    bannerImage: Painter,
    profileImage: Painter,
    profilePictureSize: Dp = ProfilePictureSizeLarge,
    onBannerClick: () -> Unit = {},
    onProfilePictureClick: () -> Unit = {}
) {
    val bannerHeight = (LocalConfiguration.current.screenWidthDp / 2.5).dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(bannerHeight + profilePictureSize / 2f)
    ) {
        Image(
            painter = bannerImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(bannerHeight)
                .clickable {
                    onBannerClick()
                }
        )
        Image(
            painter = profileImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(profilePictureSize)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colors.onSurface,
                    shape = CircleShape
                )
                .clickable {
                    onProfilePictureClick()
                },
            contentScale = ContentScale.Crop
        )
    }
}